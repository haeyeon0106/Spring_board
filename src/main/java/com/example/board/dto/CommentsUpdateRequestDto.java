package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentsUpdateRequestDto {
    private String contents;

    @Builder
    public CommentsUpdateRequestDto(String contents){
        this.contents = contents;
    }
}
