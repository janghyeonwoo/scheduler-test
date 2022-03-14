package com.example.scheduler.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ResponseDto {
    private String name;
    private Long idx;

    @QueryProjection
    public ResponseDto(String name, Long idx) {
        this.name = name;
        this.idx = idx;
    }
}
