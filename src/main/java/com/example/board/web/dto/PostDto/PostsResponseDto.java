package com.example.board.web.dto.PostDto;

import com.example.board.domain.login.Member;
import com.example.board.domain.posts.Posts;
import com.example.board.web.dto.CommentDto.CommentResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

// Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
// 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한잠조 방지
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdDate, modifiedDate;
    private Member member;
    private List<CommentResponseDto> comments;  // 리턴타입 List -> 엔티티 간 무한참조 방지

    public  PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.member = entity.getMember();
        this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
