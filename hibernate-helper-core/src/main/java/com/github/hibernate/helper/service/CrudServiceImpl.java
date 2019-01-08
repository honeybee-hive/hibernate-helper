/*
 * CrudServiceImpl  1.0  2019-01-08
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.service;

import com.github.hibernate.helper.HibernateHelper;
import com.github.hibernate.helper.condition.SQLCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 单体操作基础
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2019-01-08 zhuyan 初版
 */
public class CrudServiceImpl<T, PK extends Serializable> implements CrudService<T, PK> {

    protected Class<T> clazz;

    @Autowired
    private HibernateHelper helper;

    public Serializable save(T t) {
        return helper.save(t);
    }

    public void update(T t) {
        helper.update(t);
    }

    public void updatePatch(T t) {
        helper.updatePatch(t);
    }

    public void updatePatch(T t, List<SQLCondition> conditionParams) {
        helper.updatePatch(t, conditionParams);
    }

    public void delete(T t) {
        helper.delete(t);
    }

    public void deleteById(PK id) {
        helper.delete(helper.get(clazz, id));
    }

    public void delete(T t, List<SQLCondition> conditionParams) {
        helper.delete(t);
    }

    public T find(PK id) {
        return helper.get(clazz, id);
    }

    public List<T> find() {
        return helper.find(clazz);
    }

    public List<T> findByCondition(Object condition) {
        return helper.findCondition(clazz, condition);
    }

    public Page<T> findByPage(Integer page, Integer size) {
        return helper.findPage(clazz, page, size);
    }

    public Page<T> findByConditionPage(Object condition, Integer page, Integer size) {
        return helper.findConditionPage(clazz, condition, page, size);
    }

}
