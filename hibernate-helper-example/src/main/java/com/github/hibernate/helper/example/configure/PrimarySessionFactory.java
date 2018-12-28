/*
 * PrimarySessionFactory  1.0  2018-12-28
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.hibernate.helper.example.configure;

import com.github.hibernate.helper.HibernateHelper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * [关于类内容的描述]
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-12-28 zhuyan 初版
 */
@Configuration
public class PrimarySessionFactory {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary;

    @Bean
    @Qualifier("sessionFactory")
    public SessionFactory sessionFactory() {
        return entityManagerFactoryPrimary.getObject().unwrap(SessionFactory.class);
    }

    @Bean
    public HibernateTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean(name = "helper")
    public HibernateHelper helper(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        return new HibernateHelper(sessionFactory);
    }

}
