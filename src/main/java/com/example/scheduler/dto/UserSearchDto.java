package com.example.scheduler.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
public class UserSearchDto {
    private String name;
    private Integer age;

    private String teanName;

    @QueryProjection

    public UserSearchDto(String name, Integer age, String teanName) {
        this.name = name;
        this.age = age;
        this.teanName = teanName;
    }
}
