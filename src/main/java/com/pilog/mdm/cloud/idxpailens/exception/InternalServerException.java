package com.pilog.mdm.cloud.idxpailens.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InternalServerException extends RuntimeException {
    private String message;
    private int statusCode;
    private String errorCode;
    private LocalDateTime timestamp;
    public InternalServerException(String message, int statusCode, String errorCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}