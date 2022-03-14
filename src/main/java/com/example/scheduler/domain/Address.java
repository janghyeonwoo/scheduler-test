package com.example.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private Integer zipCode;
}
