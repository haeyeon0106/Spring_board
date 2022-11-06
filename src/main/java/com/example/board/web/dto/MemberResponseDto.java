package com.example.board.web.dto;

import com.example.board.domain.login.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final String memberName;
    private final String memberId;
    private final String memberPw;

    public MemberResponseDto(Member entity){
        this.memberName = entity.getMemberName();
        this.memberId = entity.getMemberId();
        this.memberPw = entity.getMemberPw();
    }
}
