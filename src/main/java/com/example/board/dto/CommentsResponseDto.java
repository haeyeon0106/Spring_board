package com.example.board.dto;

import com.example.board.domain.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentsResponseDto {
    private Long id;
    private String contents;
    private String memberId;
    private Long postsId;

    public CommentsResponseDto(Comments comments){
        this.id = comments.getId();
        this.contents = comments.getContents();
//        this.memberId = comments.getMember().getMemberId();
        this.postsId = comments.getPosts().getId();
    }
}
