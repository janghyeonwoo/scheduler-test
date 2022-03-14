package com.example.scheduler.error;

import com.example.scheduler.error.exception.ClientException;
import com.example.scheduler.error.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ComExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<?> clientExceptionHandle(ClientException ce){
        return ResponseEntity.status(ce.getEt().getStatus())
                .body(ErrorResponse.getErrorResponse(ce.getEt().getErrMsg(), ce.getEt().getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandle(ServerException se){
        return ResponseEntity.status(se.getErrorType().getStatus())
                             .body(ErrorResponse.getErrorResponse(
                                     se.getErrorType().getErrMsg(),se.getErrorType().getCode()));
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse hand

}
