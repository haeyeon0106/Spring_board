package com.example.board.dto;

import com.example.board.domain.Posts;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;

    private String createdAt;
    private String updatedAt;


    private List<CommentsResponseDto> comments;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
        this.createdAt = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.updatedAt = entity.getLastModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}
