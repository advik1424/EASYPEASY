package com.example.CRUDSPRINGBOOTDEMO.exception;

import com.example.CRUDSPRINGBOOTDEMO.DTO.ExceptionDto;
import com.example.CRUDSPRINGBOOTDEMO.DTO.Validationexceptionresponsedto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice


public class GlobalException {


    @ExceptionHandler(Resourcenotfoundexception.class)
    public ResponseEntity<ExceptionDto> handlresourcenotfoundexception(Resourcenotfoundexception runex, HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),runex.getMessage(),request.getRequestURI());




        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);


    }
    @ExceptionHandler(Duplicateresourseexception.class)
    public ResponseEntity<ExceptionDto> handlresourcenotfoundexception(Duplicateresourseexception runex, HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(LocalDateTime.now(),HttpStatus.CONFLICT.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),runex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);


    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleruntimeexception(RuntimeException runex, HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),runex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDto);


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handlegenricexception(Exception runex, HttpServletRequest request){

        ExceptionDto exceptionDto=new ExceptionDto(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),runex.getMessage(),request.getRequestURI());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDto);


    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Validationexceptionresponsedto> handlresourcenotfoundexception(MethodArgumentNotValidException runex, HttpServletRequest request){

        Map<String,String > fielderror=new HashMap<>();


        runex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fielderror.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );


        Validationexceptionresponsedto exceptionDto=new Validationexceptionresponsedto(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),runex.getMessage(),request.getRequestURI(),fielderror);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);


    }

}


