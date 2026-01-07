package com.device.management.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证所有异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllException extends RuntimeException {
    private int code;
    private String message;

    public AllException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }
}