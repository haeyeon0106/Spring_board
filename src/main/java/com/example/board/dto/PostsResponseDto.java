package com.example.board.dto;

import com.example.board.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;

    private List<CommentsResponseDto> comments;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}
