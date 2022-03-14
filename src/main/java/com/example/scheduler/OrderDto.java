package com.example.scheduler;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OrderDto {
    private String userName;
    private int price;

    @QueryProjection
    public OrderDto(String userName, int price) {
        this.userName = userName;
        this.price = price;
    }
}
