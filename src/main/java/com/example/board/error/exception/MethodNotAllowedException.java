package com.example.board.error.exception;

import com.example.board.error.ErrorCode;
import lombok.Getter;

@Getter
public class MethodNotAllowedException extends EntireException{
    private ErrorCode errorCode;

    public MethodNotAllowedException(String message, ErrorCode errorCode){
        super(message,errorCode);
    }
}
