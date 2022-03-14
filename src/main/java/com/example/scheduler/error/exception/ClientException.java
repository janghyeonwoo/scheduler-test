package com.example.scheduler.error.exception;

import com.example.scheduler.type.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends RuntimeException{
    private ErrorType et;
    private Exception exception;

    public ClientException(ErrorType errorType){
        super(errorType.getErrMsg());
        this.et = errorType;
    }

    public ClientException(Exception exception, ErrorType errorType) {
        super(errorType.getErrMsg());
        this.et = errorType;
        this.exception = exception;
    }
}
