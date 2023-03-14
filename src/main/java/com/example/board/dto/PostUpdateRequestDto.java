package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateRequestDto {
    private String title;
    private String contents;

    @Builder
    public PostUpdateRequestDto(String title,String contents){
        this.title = title;
        this.contents = contents;
    }
}
