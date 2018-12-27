package com.github.hibernate.helper.example;

import com.github.hibernate.helper.HibernateHelper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication(scanBasePackages = {"com.github.hibernate.helper.example"})
@EnableTransactionManagement
public class HibernateHelperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateHelperExampleApplication.class, args);
    }

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
        return transactionManager;
    }

    @Bean
    public HibernateHelper hibernateHelper() {
        return new HibernateHelper(entityManager.getEntityManagerFactory().unwrap(SessionFactory.class));
    }
}
