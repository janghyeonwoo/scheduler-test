package com.example.scheduler.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.scheduler.dto.QResponseDto is a Querydsl Projection type for ResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QResponseDto extends ConstructorExpression<ResponseDto> {

    private static final long serialVersionUID = -1029788597L;

    public QResponseDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Long> idx) {
        super(ResponseDto.class, new Class<?>[]{String.class, long.class}, name, idx);
    }

}

