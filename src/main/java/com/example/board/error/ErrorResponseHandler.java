package com.example.board.error;

import com.example.board.error.exception.BadRequestException;
import com.example.board.error.exception.InternalServerErrorException;
import com.example.board.error.exception.MethodNotAllowedException;
import com.example.board.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorResponseHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final BadRequestException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode().getCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final UserNotFoundException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode().getCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler({MethodNotAllowedException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final MethodNotAllowedException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode().getCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler({InternalError.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final InternalServerErrorException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode().getCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build());
    }
}
