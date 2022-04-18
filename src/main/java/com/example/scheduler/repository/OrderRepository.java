package com.example.scheduler.repository;

import com.example.scheduler.domain.Order;
import com.example.scheduler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , CustomOrderRepository {

    @Modifying
    @Query("DELETE FROM User u WHERE u.idx = :idx")
    void deleteByDee(@Param("idx") long idx);

}
