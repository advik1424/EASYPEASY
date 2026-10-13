package com.easypeasy.EPS_BACKEND.Exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String message){

        super(message);
    }
}
