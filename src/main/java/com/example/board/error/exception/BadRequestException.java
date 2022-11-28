package com.example.board.error.exception;

import com.example.board.error.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends EntireException{
    private ErrorCode errorCode;

    public BadRequestException(String message, ErrorCode errorCode){
        super(message,errorCode);
    }
}
