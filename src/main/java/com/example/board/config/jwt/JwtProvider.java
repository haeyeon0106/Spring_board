package com.example.board.config.jwt;

import com.example.board.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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
                .refreshTokenExpiresTime(new Date(now.getTime() + refreshExpire).getTime())
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

    public  boolean verifyToken(String token){
        try{
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(token);

            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        }catch (Exception e){
            return false;
        }
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메소드
    public Authentication getAuthentication(String token){
        // 토큰 복호화
        Claims claims = parseClaims(token);
        log.info("claims: " + claims);

        if(claims.get("name") == null){
            throw new RuntimeException("Not Authorization");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("name").toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        log.info("authorities: " + authorities);

        UserDetails userDetails = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(userDetails,"",authorities);
    }

    private Claims parseClaims(String token) {
        try{
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public String getUid(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
