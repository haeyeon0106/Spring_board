package com.example.board.web.dto.CommentDto;

import com.example.board.domain.comments.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comments;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String memberName;
    private Long postId;

    public CommentResponseDto(Comments comments){
        this.id = comments.getId();
        this.comments = comments.getComment();
        this.createdDate = comments.getCreatedDate();
        this.modifiedDate = comments.getModifiedDate();
        this.memberName = comments.getMember().getMemberName();
        this.postId = comments.getPosts().getId();
    }
}
