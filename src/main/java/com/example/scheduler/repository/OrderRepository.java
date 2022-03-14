package com.example.scheduler.repository;

import com.example.scheduler.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , CustomOrderRepository {

}
