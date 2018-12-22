/*
 * BaseDao  1.0  2018-12-03
 */
package com.github.hibernate.helper.example.dao;

import com.github.hibernate.helper.HibernateHelper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 基础DAO
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-03 zhuyan 初版
 */
public class BaseDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected HibernateHelper helper;

    @PostConstruct
    public void init() {
        helper = new HibernateHelper(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
    }

}
