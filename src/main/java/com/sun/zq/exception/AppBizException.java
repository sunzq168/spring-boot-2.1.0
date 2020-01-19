package com.sun.zq.exception;

public class AppBizException extends RuntimeException {
    public AppBizException() {
    }

    public AppBizException(String message) {
        super(message);
    }
}
