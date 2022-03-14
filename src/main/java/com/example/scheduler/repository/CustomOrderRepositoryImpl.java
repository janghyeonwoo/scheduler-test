package com.example.scheduler.repository;


import com.example.scheduler.domain.Order;
import com.example.scheduler.domain.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public String getName() {
        return "커스텀2";
    }

    @Override
    public List<Order> findAll() {
        QOrder order = new QOrder("o");
        List<Order> orders = jpaQueryFactory.select(order).from(order).limit(1).fetch();
        return orders;
    }
}
