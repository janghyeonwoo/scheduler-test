package com.example.scheduler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String errMsg;
    private String code;
    public static ErrorResponse getErrorResponse(String errMsg, String code){
        return new ErrorResponse(errMsg,code);
    }
}
