/*
 * CommonService  1.0  2019-01-08
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.service;

import com.github.hibernate.helper.condition.SQLCondition;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * [关于类内容的描述]
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2019-01-08 zhuyan 初版
 */
public interface CrudService<T, PK extends Serializable> {

    public PK save(T t);

    public void update(T t);

    public void updatePatch(T t);

    public void updatePatch(T t, List<SQLCondition> conditionParams);

    public void delete(T t);

    public void deleteById(Class<T> clazz, PK id);

    public void delete(T t, List<SQLCondition> conditionParams);

    public T get(Class<T> clazz, PK id);

    public List<T> find(Class<T> clazz);

    public List<T> findByCondition(Class<T> clazz, Object condition);

    public Page<T> findByPage(Class<T> clazz, Integer page, Integer size);

    public Page<T> findByConditionPage(Class<T> clazz, Object condition, Integer page, Integer size);

}
