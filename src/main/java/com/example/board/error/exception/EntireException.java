package com.example.board.error.exception;

import com.example.board.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntireException extends RuntimeException{
    private ErrorCode errorCode;

    public EntireException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
