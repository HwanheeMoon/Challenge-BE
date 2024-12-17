package com.challenge.challenge.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.invoke.ParameterMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<?> handleHttpMediaTypeException(HttpMediaTypeException exception) {
        log.error("Http MediaType Handled : " + exception.getMessage());
        return ResponseEntity.ok(new CommonException(ErrorCode.NOT_END_POINT));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("Http RequestMethod Not Supported : " + exception.getMessage());
        return ResponseEntity.ok(new CommonException(ErrorCode.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(ParameterMappingException.class)
    public ResponseEntity<?> handleParameterMappingException(ParameterMappingException exception) {
        log.error("Parameter Mapping Exception : " + exception.getMessage());
        return ResponseEntity.ok(new CommonException(ErrorCode.MISSING_REQUEST_PARAMETER));
    }

}
