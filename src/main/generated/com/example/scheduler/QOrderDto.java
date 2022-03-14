package com.example.scheduler;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.scheduler.QOrderDto is a Querydsl Projection type for OrderDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QOrderDto extends ConstructorExpression<OrderDto> {

    private static final long serialVersionUID = -742584523L;

    public QOrderDto(com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<Integer> price) {
        super(OrderDto.class, new Class<?>[]{String.class, int.class}, userName, price);
    }

}

