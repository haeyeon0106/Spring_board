package com.example.board.error.exception;

import com.example.board.error.ErrorCode;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends EntireException {
    private ErrorCode errorCode;

    public InternalServerErrorException(String message, ErrorCode errorCode){
        super(message,errorCode);
    }
}
