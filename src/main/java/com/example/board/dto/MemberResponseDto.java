package com.example.board.dto;

import com.example.board.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponseDto {

    private Long id;
    private String memberId;
    private String name;

    public MemberResponseDto(Member entity){
        this.id = entity.getId();
        this.memberId = entity.getMemberId();
        this.name = entity.getName();
    }
}
