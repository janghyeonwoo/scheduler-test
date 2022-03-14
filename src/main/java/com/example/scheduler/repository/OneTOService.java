package com.example.scheduler.repository;

import org.springframework.stereotype.Service;

@Service
public class OneTOService implements DoubleRepo{
    @Override
    public String getName() {
        return "안녕..";
    }
}
