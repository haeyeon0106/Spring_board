package com.example.board.exception.custom;

import com.example.board.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InfoException extends RuntimeException{

    private final ErrorCode errorCode;
    private final String errorMessage;

}
