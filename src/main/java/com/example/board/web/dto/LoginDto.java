package com.example.board.web.dto;

import com.example.board.domain.login.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {

    private String memberId;
    private String memberPw;

    public Member toEntity(){
        return Member.builder().memberId(memberId).memberPw(memberPw).build();
    }
}
