package com.whitle6.webfluxdemo.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -123L;


    public CustomException(String message) {
        super(message);
        log.info("CustomException: {}", message);
    }
}
