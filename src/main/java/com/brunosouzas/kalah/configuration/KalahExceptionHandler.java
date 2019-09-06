package com.brunosouzas.kalah.configuration;

import com.brunosouzas.kalah.domain.exception.Exception;
import com.brunosouzas.kalah.domain.exception.KalahException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KalahExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {KalahException.class})
    protected ResponseEntity<Exception> handleKalahException(KalahException ex, WebRequest request) {

        return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.BAD_REQUEST);

    }
}
