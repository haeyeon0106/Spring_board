package com.example.board.exception;

import com.example.board.exception.custom.InfoException;
import com.example.board.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMemberException(InfoException ie){
        log.info("MemberException: ",ie);
        ErrorResponse errorResponse = new ErrorResponse(ie.getErrorCode(),ie.getErrorMessage());
        return new ResponseEntity<>(errorResponse,ie.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePostException(InfoException ie){
        log.info("PostException: ",ie);
        ErrorResponse errorResponse = new ErrorResponse(ie.getErrorCode(),ie.getErrorMessage());
        return new ResponseEntity<>(errorResponse,ie.getErrorCode().getHttpStatus());
    }
}
