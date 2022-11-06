package com.example.board.web.dto;

import com.example.board.domain.login.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String memberName;
    private String memberId;
    private String memberPw;

    public Member toEntity(){
        return Member.builder().memberName(memberName).memberId(memberId).memberPw(memberPw).build();
    }
}
