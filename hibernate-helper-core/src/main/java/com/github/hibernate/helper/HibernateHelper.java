package com.github.hibernate.helper;

import com.github.hibernate.helper.annotation.AnnotationUtil;
import com.github.hibernate.helper.condition.QueryCondition;
import com.github.hibernate.helper.condition.SQLCondition;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Hibernate基础处理类
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-12-9 zy 初版
 */
@Slf4j
public class HibernateHelper {

    //@Autowired
    private SessionFactory sessionFactory;


    public HibernateHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public SessionFactory getSessionFactory() {
//        return this.sessionFactory;
//    }

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 保存
     *
     * @param entity
     * @return Serializable 主键
     */
    public Serializable save(Object entity) {
        return this.getCurrentSession().save(entity);
    }

    /**
     * 全部更新
     *
     * @param entity
     */
    public void update(Object entity) {
        this.getCurrentSession().update(entity);
    }

    /**
     * 部分更新 <br>
     * 通过注解更新对象中不为Null的值
     *
     * @param entity 更新类
     * @return int 返回更新数量
     */
    public int updatePatch(Object entity) {
        SQLCondition sqlCondition = AnnotationUtil.getTableId(entity);
        List<SQLCondition> conditionParams = ImmutableList.of(sqlCondition);
        return this.updatePatch(entity, conditionParams);
    }

    /**
     * 部分更新 <br>
     * 通过注解更新对象中不为Null的值
     *
     * @param entity          更新类
     * @param conditionParams 自定义更新条件
     * @return int 返回更新数量
     */
    public int updatePatch(Object entity, List<SQLCondition> conditionParams) {
        if (entity == null) {
            throw new NullPointerException("update object not null");
        }
        Map<String, Object> updateParamMap = HelperUtils.objectToMap(entity);
        // 前置条件参数验证
        if (null == updateParamMap || 0 == updateParamMap.size()) {
            throw new NullPointerException("update updateParamMap not null");
        }

        //String aliasName = entity.getClass().getSimpleName();

        // 组织UPDATE-HQL语句
        StringBuffer updateSQL = new StringBuffer(
                "UPDATE " + AnnotationUtil.getTableName(entity.getClass()) + " SET");

        // 拼接更新语句
        Iterator<Entry<String, Object>> entryKeyIterator = updateParamMap.entrySet().iterator();
        for (int i = 0; entryKeyIterator.hasNext(); i++) {
            Entry<String, Object> e = entryKeyIterator.next();
            String updateName = e.getKey();
            String columnName = HelperUtils.toColumn(updateName);
            if (i < updateParamMap.size() - 1) {
                updateSQL.append(" " + columnName + "=:" + updateName + ",");
            } else {
                updateSQL.append(" " + columnName + "=:" + updateName);
            }
        }
        if (conditionParams == null || conditionParams.size() == 0) {
            throw new NullPointerException("conditionParams not null or size is zero");
        }
        // 拼接条件语句
        for (int i = 0; i < conditionParams.size(); i++) {
            SQLCondition conditionParam = conditionParams.get(i);
            // 条件参数名
            String conditionName = conditionParam.getName();
            String symbol = conditionParam.getSymbol();
            Object conditionValue = conditionParam.getValue();
            updateParamMap.put(conditionName, conditionValue);
            if (i == 0) {
                updateSQL.append(" WHERE " + conditionName + symbol + ":" + conditionName);
            } else {
                updateSQL.append(" AND " + conditionName + symbol + ":" + conditionName);
            }
        }
        log.info("update-patch-sql：" + updateSQL.toString());
        NativeQuery q = this.getCurrentSession().createNativeQuery(updateSQL.toString());
        // 设计条件值
        q.setProperties(updateParamMap);
        // 执行更新操作
        int result = q.executeUpdate();

        // Todo:设置更新拦截器接口扩展使用(日志、共通字段等处理)

        return result;
    }

    /**
     * 保存或更新
     */
    public void saveOrUpdate(Object entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }


    /**
     * 刪除操作
     */
    public void delete(Object entity) {
        this.getCurrentSession().delete(entity);
    }

    /**
     * 单表主键查询
     *
     * @param clazz ORM对象
     * @param id    主键
     * @return Object ORM对象
     */
    public <T> T get(Class<T> clazz, Serializable id) {
        return (T) this.getCurrentSession().get(clazz, id);
    }

    /**
     * 单表主键查询<br>
     * 1、增加数据锁<br>
     * 2、事务结束后，锁自动释放
     *
     * @param clazz       ORM对象
     * @param id          主键
     * @param lockOptions 锁
     * @return Object ORM对象
     */
    public <T> T get(Class<T> clazz, Serializable id, LockOptions lockOptions) {
        return (T) this.getCurrentSession().get(clazz, id, lockOptions);
    }

    /**
     * 查询
     *
     * @param clazz 返回类
     * @return List<T>
     */
    public <T> List<T> find(Class clazz) {

        // 默认取类名作为别名
        final String aliasName = clazz.getSimpleName();//"entity";

        String tableName = AnnotationUtil.getTableName(clazz);
        if (StringUtils.isEmpty(tableName)) {
            throw new RuntimeException("Not Table Entity");
        }
        String sqlFields = SQLHelper.getSQLFields(clazz, aliasName);
        //QueryCondition queryCondition = HelperUtils.getQueryCondition(entity, aliasName);

        String sql = "SELECT "
                + SQLHelper.getSQLFields(clazz, aliasName)
                + "       FROM " + tableName + " AS " + aliasName;

        log.info("find-sql:" + sql);

        return this.getCurrentSession()
                .createSQLQuery(sql).unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(clazz))
                .list();
    }

    /**
     * 条件查询
     *
     * @param clazz     返回类
     * @param condition 检索条件类
     * @return List<T>
     */
    public <T> List<T> findCondition(Class<T> clazz, Object condition) {

        // 默认取类名作为别名
        final String aliasName = clazz.getSimpleName();//"entity";

        String tableName = AnnotationUtil.getTableName(clazz);
        if (StringUtils.isEmpty(tableName)) {
            throw new RuntimeException("Not Table Entity");
        }
        String sqlFields = SQLHelper.getSQLFields(clazz, aliasName);
        QueryCondition queryCondition = SQLHelper.getQueryCondition(condition, aliasName);

        String sql = "SELECT "
                + SQLHelper.getSQLFields(clazz, aliasName)
                + "       FROM " + tableName + " AS " + aliasName
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

        log.info("find-sql:" + sql);

        return this.getCurrentSession().createSQLQuery(sql).
                setProperties(queryCondition.getQueryParams()).
                unwrap(NativeQueryImpl.class).
                setResultTransformer(Transformers.aliasToBean(clazz)).
                list();
    }

    /**
     * 分页查询
     *
     * @param clazz 返回类
     * @param page  当前页数
     * @param size  返回条数
     * @return
     */
    public <T> Page<T> findPage(Class<T> clazz, Integer page, Integer size) {

        // 默认取类名作为别名
        final String aliasName = clazz.getSimpleName();//"entity";

        String tableName = AnnotationUtil.getTableName(clazz);
        if (StringUtils.isEmpty(tableName)) {
            throw new RuntimeException("No Mapping Table Entity");
        }
        String sqlFields = SQLHelper.getSQLFields(clazz, aliasName);

        String sql = "SELECT "
                + SQLHelper.getSQLFields(clazz, aliasName)
                + "       FROM " + tableName + " AS " + aliasName;

        log.info("find-page-sql:" + sql);

        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        List results = this.getCurrentSession().createSQLQuery(sql)
                .setFirstResult(page * size).setMaxResults(size)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(clazz))
                .list();

        String countSql = "SELECT COUNT(*)"
                + "       FROM " + tableName + " AS " + aliasName;

        log.info("find-page-count-sql:" + sql);

        Optional<BigInteger> integerOption = this.getCurrentSession()
                .createSQLQuery(countSql)
                .uniqueResultOptional();

        int total = integerOption == null ? 0 : integerOption.get().intValue();

        Page<T> pageResult = new PageImpl(results, PageRequest.of(page, size), total);

//        Pager pager = new Pager(page, size, total);
//        pager.setDatas(results);

        return pageResult;
    }

    /**
     * 分页条件查询
     *
     * @param clazz     返回类
     * @param condition 检索条件类
     * @param page      当前页数
     * @param size      返回条数
     * @return
     */
    public <T> Page<T> findConditionPage(Class<T> clazz, Object condition, Integer page, Integer size) {

        // 默认取类名作为别名
        final String aliasName = clazz.getSimpleName();//"entity";

        String tableName = AnnotationUtil.getTableName(clazz);
        if (StringUtils.isEmpty(tableName)) {
            throw new RuntimeException("No Mapping Table Entity");
        }
        String sqlFields = SQLHelper.getSQLFields(clazz, aliasName);
        QueryCondition queryCondition = SQLHelper.getQueryCondition(condition, aliasName);

        String sql = "SELECT "
                + SQLHelper.getSQLFields(clazz, aliasName)
                + "       FROM " + tableName + " AS " + aliasName
                + queryCondition.getQueryString()
                + queryCondition.getOrderString();

        log.info("find-page-sql:" + sql);

        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        List results = this.getCurrentSession().createSQLQuery(sql)
                .setFirstResult(page * size).setMaxResults(size)
                .setProperties(queryCondition.getQueryParams())
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(clazz))
                .list();

        String countSql = "SELECT COUNT(*)"
                + "       FROM " + tableName + " AS " + aliasName
                + queryCondition.getQueryString();

        log.info("find-page-count-sql:" + sql);

        Optional<BigInteger> integerOption = this.getCurrentSession()
                .createSQLQuery(countSql)
                .setProperties(queryCondition.getQueryParams())
                .uniqueResultOptional();

        int total = integerOption == null ? 0 : integerOption.get().intValue();

        Page<T> pageResult = new PageImpl(results, PageRequest.of(page, size), total);

//        Pager pager = new Pager(page, size, total);
//        pager.setDatas(results);

        return pageResult;
    }

    /**
     * 查询
     *
     * @param sql       查询语句
     * @param clazz     返回类
     * @param condition 检索条件
     * @return
     */
    public <T> List<T> findBySQL(String sql, Class<T> clazz, Map<String, Object> condition) {
        NativeQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (condition != null && condition.size() > 0) {
            q.setProperties(condition);
        }

        return q.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    /**
     * 查询
     *
     * @param sql       查询语句
     * @param countSql  分页语句
     * @param clazz     返回类
     * @param condition 检索条件
     * @param page      当前页数
     * @param size      返回条数
     * @return
     */
    public <T> Page<T> findPageBySQL(String sql, String countSql, Class<T> clazz, Map<String, Object> condition, Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        List<T> results = this.getCurrentSession().createSQLQuery(sql)
                .setProperties(condition)
                .setFirstResult(page * size).setMaxResults(size)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(clazz)).list();

        Optional<BigInteger> integerOption = this.getCurrentSession()
                .createSQLQuery(countSql)
                .setProperties(condition)
                .uniqueResultOptional();

        int total = integerOption == null ? 0 : integerOption.get().intValue();

        Page<T> pageResult = new PageImpl(results, PageRequest.of(page, size), total);

//        Pager pager = new Pager(page, size, total);
//        pager.setDatas(results);

        return pageResult;
    }

    /**
     * SQL执行语句
     *
     * @param sql
     * @return Integer 操作记录数
     */
    public Integer executeSQL(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    /**
     * SQL执行条件语句
     *
     * @param sql
     * @return Integer 操作记录数
     */
    public Integer executeSQL(String sql, Map<String, Object> condition) {
        NativeQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (condition != null && condition.size() > 0) {
            q.setProperties(condition);
        }
        return q.executeUpdate();
    }

    /**
     * SQL-count函数统计语句
     *
     * @param sql
     * @return 记录数
     */
    public int count(String sql) {
        Optional<Integer> integerOption = this.getCurrentSession().createSQLQuery(sql).uniqueResultOptional();
        return integerOption.get();
    }

    /**
     * SQL-count函数统计条件语句
     *
     * @param sql
     * @param condition
     * @return 记录数
     */
    public Integer count(String sql, Map<String, Object> condition) {
        NativeQuery nativeQuery = this.getCurrentSession().createSQLQuery(sql);
        if (condition != null && condition.size() > 0) {
            nativeQuery.setProperties(condition);
        }
        Optional<Integer> integerOption = nativeQuery.uniqueResultOptional();
        return integerOption.get();
    }
}
