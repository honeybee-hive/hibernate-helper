package com.github.hibernate.helper.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.hibernate.helper.example"})
public class HibernateHelperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateHelperExampleApplication.class, args);
    }

}
