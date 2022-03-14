package com.example.scheduler.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class OneTo2Service implements DoubleRepo{
    @Override
    public String getName() {
        return "나는 두번째";
    }
}
