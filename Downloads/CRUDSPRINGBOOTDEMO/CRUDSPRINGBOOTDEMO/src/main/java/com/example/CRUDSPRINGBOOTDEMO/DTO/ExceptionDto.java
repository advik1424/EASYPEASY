package com.example.CRUDSPRINGBOOTDEMO.DTO;

import java.time.LocalDateTime;

public class ExceptionDto {
    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;
    private  String path;

    public ExceptionDto(LocalDateTime timestamp, int statusCode, String path, String message, String error) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
        this.error = error;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
