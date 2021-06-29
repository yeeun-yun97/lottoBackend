package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.SimpleErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SimpleExceptionHandler {

    @ExceptionHandler(SimpleException.class)
    protected ResponseEntity<SimpleErrorResponse> handleSimpleException(SimpleException e){
        log.error(e.getClass().getSimpleName(),e);
        SimpleErrorResponse response= SimpleErrorResponse
                .builder()
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<SimpleErrorResponse> handleUsernameNotFoundException(UserNotFoundException e){
        log.error(e.getClass().getSimpleName(),e);
        SimpleErrorResponse response= SimpleErrorResponse
                .builder()
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }



}
