package com.example.scheduler.repository;

import com.example.scheduler.domain.Order;
import com.sun.xml.bind.v2.model.core.ID;

import java.util.List;


public interface CustomOrderRepository {
    public List<Order> findAll();
    public String getName();
}
