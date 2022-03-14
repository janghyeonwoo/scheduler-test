package com.example.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.ref.PhantomReference;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends DateTime{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ord_no")
    private String ordNo;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private User user;

    @Embedded
    private Address address;

}
