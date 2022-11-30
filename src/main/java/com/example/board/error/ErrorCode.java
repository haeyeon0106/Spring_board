package com.example.board.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    // 400 BAD_REQUEST : 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"E0001","잘못된 요청입니다."),

    // 404 NOT_FOUND : 리소스를 찾을 수 없음
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"E0002","유저를 찾을 수 없습니다."),

    // 405 METHOD_NOT_ALLOWED : 허용되지 않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"E0003","허용되지 않은 메소드입니다."),

    // 500 INTERNAL_SERVER_ERROR : 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"E0004","내부 서버 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
