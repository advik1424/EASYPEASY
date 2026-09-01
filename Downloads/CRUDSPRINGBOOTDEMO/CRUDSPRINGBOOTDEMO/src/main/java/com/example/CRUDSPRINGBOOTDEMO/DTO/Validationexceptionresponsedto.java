package com.example.CRUDSPRINGBOOTDEMO.DTO;

import java.time.LocalDateTime;
import java.util.Map;

public class Validationexceptionresponsedto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;
    private  String path;
    private Map<String,String> filedbyerror;

    public Validationexceptionresponsedto(LocalDateTime timestamp, int statusCode, String path, String message, String error, Map<String, String> filedbyerror) {
        this.timestamp = timestamp;
        this.filedbyerror = filedbyerror;
        this.path = path;
        this.message = message;
        this.error = error;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getFiledbyerror() {
        return filedbyerror;
    }

    public void setFiledbyerror(Map<String, String> filedbyerror) {
        this.filedbyerror = filedbyerror;
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
}
