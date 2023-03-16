package com.example.board.config.jwt;

import com.example.board.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JwtProvider {
    private final Key secretKey;
    private final long accessExpire = 1000 * 60 * 30; // 30분
    private final long refreshExpire = 1000 * 60 * 60 * 24 * 14; // 2주

    public JwtProvider(@Value("${jwt.secret}") String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public TokenDto generateToken(String uid,String name){

        Claims claims = Jwts.claims().setSubject(uid); // sub(subject) : 토큰제목
        claims.put("name",name);

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)                                      // payload "role": "ROLE_USER"
                .setExpiration(new Date(now.getTime() + accessExpire))  // payload "exp" : 14234532(예시)
                .signWith(secretKey)                 // header  "alg" : "HS256"
                .compact();

        String refreshToken = refreshToken(uid);

        log.info("AccessToken : " + accessToken);
        log.info("RefreshToken : " + refreshToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public String refreshToken(String uid){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(uid)
                .setExpiration(new Date(now.getTime() + refreshExpire))
                .signWith(secretKey)
                .compact();
    }

}
