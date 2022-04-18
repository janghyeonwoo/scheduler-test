package com.example.scheduler.repository;

import com.example.scheduler.dto.UserSearchDto;
import com.querydsl.core.QueryResults;

public interface CustomUserRepository {
    public QueryResults<UserSearchDto> findSearch(UserSearchDto searchDto);
}
