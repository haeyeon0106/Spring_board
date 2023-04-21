package com.example.board.dto;

import com.example.board.domain.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CommentsResponseDto {
    private Long id;
    private String contents;
    private String memberId;
    private Integer depth;
    private Long postsId;
    private List<CommentsResponseDto> child;

    public CommentsResponseDto(Comments comments){
        this.id = comments.getId();
        this.contents = comments.getContents();
//        this.memberId = comments.getMember().getMemberId();
        this.depth = comments.getDepth();
        this.postsId = comments.getPosts().getId();
        this.child = comments.getChild().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}
