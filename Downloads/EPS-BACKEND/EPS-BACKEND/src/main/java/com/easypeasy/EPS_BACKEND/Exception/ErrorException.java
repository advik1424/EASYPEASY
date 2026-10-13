package com.easypeasy.EPS_BACKEND.Exception;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.id.enhanced.LegacyHiLoAlgorithmOptimizer;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorException {

    private int Status;
    private String message;
    private LocalDateTime localDateTime;

    private Map<String,String> errors;







}
