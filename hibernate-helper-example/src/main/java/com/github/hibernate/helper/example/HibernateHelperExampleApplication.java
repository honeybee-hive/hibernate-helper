package com.github.hibernate.helper.example;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.hibernate.helper.HibernateHelper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = {"com.github.hibernate.helper.example", "com.github.hibernate.helper"})
public class HibernateHelperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateHelperExampleApplication.class, args);
    }

//    @Autowired
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid")
//    public DataSource dataSourceOne() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerPrimary")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
//    }



//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
//        return transactionManager;
//    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        return entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
//    }
}
