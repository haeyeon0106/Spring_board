package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class MemberUpdateDto {
    String name;

    @Builder
    public MemberUpdateDto(String name){
        this.name = name;
    }
}
