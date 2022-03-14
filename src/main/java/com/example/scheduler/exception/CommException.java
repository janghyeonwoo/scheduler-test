package com.example.scheduler.exception;

import org.springframework.core.NestedRuntimeException;

public class CommException extends NestedRuntimeException {
    public CommException(String msg) {
        super(msg);
    }
    public CommException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
