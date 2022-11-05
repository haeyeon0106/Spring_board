package com.example.board.web.dto;

import com.example.board.domain.login.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    private String memberId;
    private String memberPw;

    @Builder
    public LoginDto(String memberId, String memberPw){
        this.memberId = memberId;
        this.memberPw = memberPw;
    }

    public Member toEntity(){
        return Member.builder().memberId(memberId).memberPw(memberPw).build();
    }
}
