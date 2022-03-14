package com.example.scheduler.repository;

import com.example.scheduler.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface TestRepo {
    List<User> findALl();
    User getUser(Long idx);
    int insertUser(@Param("name") String name);
}
