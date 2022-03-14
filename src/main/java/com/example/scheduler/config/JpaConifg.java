package com.example.scheduler.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class JpaConifg {

    @Bean
    public JPAQueryFactory setJpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
