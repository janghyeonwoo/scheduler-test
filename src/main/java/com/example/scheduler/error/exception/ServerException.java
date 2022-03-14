package com.example.scheduler.error.exception;

import com.example.scheduler.type.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServerException extends RuntimeException{
    private ErrorType errorType;
    private Exception exception;

    public ServerException(ErrorType et) {
        super(et.getErrMsg());
        this.errorType = et;
    }

    public ServerException(ErrorType et, Exception exception) {
        super(et.getErrMsg());
        this.errorType = et;
        this.exception = exception;
    }
}
