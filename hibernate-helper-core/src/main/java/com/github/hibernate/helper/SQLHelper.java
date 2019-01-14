/*
 * ConditionUtils.java	 1.0   2014-12-11
 *
 * 沈阳成林健康科技有限公司
 *
 */

package com.github.hibernate.helper;

import com.github.hibernate.helper.annotation.SelectWhere;
import com.github.hibernate.helper.annotation.SelectColumn;
import com.github.hibernate.helper.condition.QueryCondition;
import com.github.hibernate.helper.condition.SQLOrder;
import com.github.hibernate.helper.condition.SymbolConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Helper工具
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2014-12-11 zy 初版
 */
@Slf4j
public class SQLHelper {

    /**
     * 获取SQL字段
     *
     * @param queryClass 检索的类
     * @return
     */
    public static String getSQLFields(Class<?> queryClass) {
        return SQLHelper.getSQLFields(queryClass, queryClass.getSimpleName());
    }

    /**
     * 获取SQL字段
     *
     * @param queryClass 检索的类
     * @param aliasName  表名(注:如果原生SQL使用了别名,请设置别名)
     * @return
     */
    public static String getSQLFields(Class<?> queryClass, String aliasName) {
        String sqlField = SQLHelper.getRecursionFields(queryClass, aliasName);
        if (StringUtils.isNotEmpty(sqlField)) {
            // 替换首个，号
            return sqlField.toString().replaceFirst(",", " ");
        } else {
            return "";
        }
    }

    private static String getRecursionFields(Class<?> queryClass, String aliasName) {
        if (queryClass == null) {
            throw new NullPointerException("param queryClass not null");
        }
        // 查询字段语句
        StringBuffer queryBuffer = new StringBuffer("");
        if (queryClass.getName().equals("java.lang.Object")) {
            return "";
        } else {
            // 递归调用
            queryBuffer.append(SQLHelper.getRecursionFields(queryClass.getSuperclass(), aliasName));
            Field[] fields = queryClass.getDeclaredFields();
            Field.setAccessible(fields, true);
            for (Field field : fields) {
                SelectColumn queryFieldAnnotation = field.getAnnotation(SelectColumn.class);
                String alias = field.getName();
                if (alias.equals("serialVersionUID")) {
                    continue;
                }
                if (queryFieldAnnotation == null) {
                    // 默认字段名就是表字段名组合方式[类名.字段名]
                    String fieldName = HelperUtils.toColumn(field.getName());
                    fieldName = aliasName + "." + fieldName;
                    queryBuffer.append("," + fieldName + " AS " + alias);
                    continue;
                }
                boolean ignore = queryFieldAnnotation.ignore();
                if (ignore) {
                    // 忽略字段
                    continue;
                }
                String fieldName = HelperUtils.toColumn(queryFieldAnnotation.fieldName());

                // 设置别名
                String queryAliasName = StringUtils.isEmpty(queryFieldAnnotation.aliasName())
                        ? aliasName : queryFieldAnnotation.aliasName();
                if (StringUtils.isEmpty(fieldName)) {
                    // 默认字段名就是表字段名组合方式[类名.字段名]
                    fieldName = HelperUtils.toColumn(field.getName());
                    fieldName = queryAliasName + "." + fieldName;
                } else {
                    fieldName = queryAliasName + "." + fieldName;
                }
                queryBuffer.append("," + fieldName + " AS " + alias);
            }
            return queryBuffer.toString();
        }
    }

    /**
     * 通过注解与类获取检索条件(检索条件不支持复合类)
     *
     * @param conditionInstance 检索条件实体类
     * @param aliasName         别名
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static QueryCondition getQueryCondition(Object conditionInstance, String aliasName) {
        // 定义查询结果类
        QueryCondition condition = new QueryCondition();
        if (conditionInstance == null) {
            return condition;
        }
        if (StringUtils.isEmpty(aliasName)) {
            aliasName = "";
        }
        // 定义查询结果检索语句
        StringBuffer queryBuffer = new StringBuffer("");
        // 定义排序语句
        StringBuffer orderBuffer = new StringBuffer("");
        // 定义查询结果检索条件键与值
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        // 定义排序List
        List<SQLOrder> orderList = new ArrayList<SQLOrder>();

        // 获取查询条件实体类字段
        Field[] fields = HelperUtils.getWholeFields(conditionInstance.getClass());
        Field.setAccessible(fields, true);

        for (Field field : fields) {
            // 获取查询条件实体类字段QueryConditionAnnotation注解
            SelectWhere queryConditionAnnotation = field.getAnnotation(SelectWhere.class);
            if (queryConditionAnnotation == null) {
                continue;
            }
            // 检索字段名
            String name = queryConditionAnnotation.name();
            // 别名
            String queryAliasName = StringUtils.isEmpty(queryConditionAnnotation.aliasName()) ? aliasName
                    : queryConditionAnnotation.aliasName();
            // 检索条件
            String symbol = queryConditionAnnotation.symbol();
            // 前置条件验证及设置默认值
            if (StringUtils.isEmpty(symbol)) {
                symbol = SymbolConstants.EQ;
            }
            if (StringUtils.isEmpty(name)) {
                // SQL字段名
                name = queryAliasName + "." + HelperUtils.toColumn(field.getName());
            } else {
                // SQL字段名
                name = queryAliasName + "." + HelperUtils.toColumn(name);
            }
            // 排序条件
            String order = queryConditionAnnotation.order();
            // 排序顺序
            int orderNumber = queryConditionAnnotation.orderNumber();
            // 设置排序字段集合
            if (!StringUtils.isEmpty(order)) {
                orderList.add(SQLOrder.builder(name, order, orderNumber));
            }

            Object value = null;
            // 获取检索条件的值
            try {
                value = field.get(conditionInstance);
            } catch (Exception e) {
                log.error("[工具类]获取检索条件失败", e);
                throw new RuntimeException("[工具类]获取检索条件失败");
            }
            if (value == null) {
                // 类检索空
                continue;
            }
            if (value.getClass().isInstance(new String()) && StringUtils.isEmpty(value.toString())) {
                // 字符串检索值空-不检索
                continue;
            }
            // 前置条件都满足，组合检索条件（目前默认全是AND查询）
            if (symbol.equals(SymbolConstants.EQ)) {
                queryBuffer.append(" AND " + name + "=:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.NEQ)) {
                queryBuffer.append(" AND " + name + "!=:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.IS_EMPTY)
                    && "java.lang.Boolean".equalsIgnoreCase(field.getType().getName())) {
                if ((boolean) value) {
                    queryBuffer.append(" AND (ISNULL(" + name + ") " + " OR " + name + "='' )");
                }
            }
            if (symbol.equals(SymbolConstants.NOT_EMPTY)
                    && "java.lang.Boolean".equalsIgnoreCase(field.getType().getName())) {
                if ((boolean) value) {
                    queryBuffer.append(" AND " + name + "!=''");
                }
            }
            if (symbol.equals(SymbolConstants.GT)) {
                queryBuffer.append(" AND " + name + ">:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.GE)) {
                queryBuffer.append(" AND " + name + ">=:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.LT)) {
                queryBuffer.append(" AND " + name + "<:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.LE)) {
                queryBuffer.append(" AND " + name + "<=:" + field.getName());
            }
            if (symbol.equals(SymbolConstants.LIKE)) {
                queryBuffer.append(" AND " + name + " LIKE:" + field.getName());
                value = "%" + value + "%";
            }
            if (symbol.equals(SymbolConstants.LIKE_BEFORE)) {
                queryBuffer.append(" AND " + name + " LIKE:" + field.getName());
                value = "%" + value;
            }
            if (symbol.equals(SymbolConstants.LIKE_AFTER)) {
                queryBuffer.append(" AND " + name + " LIKE:" + field.getName());
                value = value + "%";
            }
            if (symbol.equals(SymbolConstants.IN)) {
                if (value instanceof String) {
                    value = value.toString().split(",");
                }
                queryBuffer.append(" AND " + name + " IN(:" + field.getName() + ")");
            }
            if (value instanceof List) {
                if (CollectionUtils.isNotEmpty((List) value)) {
                    value = ((List) value).toArray();
                } else {
                    value = "''";
                }
            }
            // 设置检索条件名与值
            queryParams.put(field.getName(), value);
        }

        String queryString = queryBuffer.toString();
        if (StringUtils.isEmpty(queryString)) {
            return condition;
        }
        // 替换多余的首个AND关键字
        queryString = queryString.replaceFirst(" AND ", " ");
        // 设置返回值
        condition.setQueryString(" WHERE " + queryString);
        condition.setQueryParams(queryParams);

        if (null != orderList && orderList.size() > 0) {
            // 当多个排序条件则按照排序号进行排序
            ArrayList<BeanComparator> sortFields = new ArrayList<BeanComparator>();
            Comparator comparator = ComparableComparator.getInstance();
            comparator = ComparatorUtils.reversedComparator(comparator); // 允许null
            sortFields.add(new BeanComparator("orderNumber", comparator)); // 主排序（第一排序）
            // 创建一个排序链
            ComparatorChain multiSort = new ComparatorChain(sortFields);
            // 开始真正的排序，按照先主，后副的规则
            Collections.sort(orderList, multiSort);
            orderBuffer.append(" ORDER BY");
            // 组织排序ORDER BY 语句
            for (int i = 0; i < orderList.size(); i++) {
                SQLOrder hqlOrder = orderList.get(i);
                orderBuffer.append(" " + hqlOrder.getFieldName() + " " + hqlOrder.getOrder());
                if (i < orderList.size() - 1) {
                    orderBuffer.append(",");
                }
            }
            condition.setOrderString(orderBuffer.toString());
        }
        return condition;
    }


}
