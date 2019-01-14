/*
 * CrudServiceImpl  1.0  2019-01-08
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.service;

import com.github.hibernate.helper.HibernateHelper;
import com.github.hibernate.helper.condition.SQLWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class CrudServiceImpl<T, PK extends Serializable> implements CrudService<T, PK> {


    @Autowired
    private HibernateHelper helper;

    @Transactional
    public PK save(T t) {
        return (PK) helper.save(t);
    }

    @Transactional
    public void update(T t) {
        helper.update(t);
    }

    @Transactional
    public void updatePatch(T t) {
        helper.updatePatch(t);
    }

    @Transactional
    public void updatePatch(T t, List<SQLWhere> conditionParams) {
        helper.updatePatch(t, conditionParams);
    }

    @Transactional
    public void delete(T t) {
        helper.delete(t);
    }

    @Transactional
    public void deleteById(Class<T> clazz, PK id) {
        helper.delete(helper.get(clazz, id));
    }

    @Transactional
    public void delete(T t, List<SQLWhere> conditionParams) {
        helper.delete(t);
    }

    @Transactional
    public T get(Class<T> clazz, PK id) {
        return helper.get(clazz, id);
    }

    @Transactional
    public List<T> find(Class<T> clazz) {
        return helper.find(clazz);
    }

    @Transactional
    public List<T> findByCondition(Class<T> clazz, Object condition) {
        return helper.findCondition(clazz, condition);
    }

    @Transactional
    public Page<T> findByPage(Class<T> clazz, Integer page, Integer size) {
        return helper.findPage(clazz, page, size);
    }

    @Transactional
    public Page<T> findByConditionPage(Class<T> clazz, Object condition, Integer page, Integer size) {
        return helper.findConditionPage(clazz, condition, page, size);
    }

}
