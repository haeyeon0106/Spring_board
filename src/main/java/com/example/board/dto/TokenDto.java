package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private long refreshTokenExpiresTime;
}
