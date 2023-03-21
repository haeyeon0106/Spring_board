package com.example.board.dto;

import com.example.board.domain.Comments;
import com.example.board.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentsRequestDto {
    private Long id;
    private String contents;
    private Posts posts;
//    private Member member;

    @Builder
    public CommentsRequestDto(String contents,Posts posts){
        this.contents = contents;
        this.posts = posts;
//        this.member = member;
    }
    public Comments toEntity(){
        return Comments.builder()
                .contents(contents)
                .posts(posts)
//                .member(member)
                .build();
    }
}
