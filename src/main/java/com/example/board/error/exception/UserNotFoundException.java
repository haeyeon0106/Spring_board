package com.example.board.error.exception;

import com.example.board.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends EntireException{
    private ErrorCode errorCode;

    public UserNotFoundException(String message, ErrorCode errorCode){
        super(message,errorCode);
    }
}
