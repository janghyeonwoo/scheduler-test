package com.example.scheduler.domain;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Entity
public class item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
}
