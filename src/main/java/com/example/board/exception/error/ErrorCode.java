package com.example.board.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@ToString
public enum ErrorCode{

    BAD_REQUEST(400,HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    NOT_FOUND(404,HttpStatus.NOT_FOUND,"리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405,HttpStatus.METHOD_NOT_ALLOWED,"허용되지 않은 메소드입니다."),
    INTERNAL_SERVER_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버 오류입니다.")

    ;
    private int code;
    private HttpStatus httpStatus;
    private String message;
}
