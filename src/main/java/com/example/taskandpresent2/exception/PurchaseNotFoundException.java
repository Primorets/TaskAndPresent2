package com.example.taskandpresent2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
