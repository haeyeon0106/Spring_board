package com.example.board.web;

import com.example.board.service.comments.CommentsService;
import com.example.board.web.dto.CommentDto.CommentRequestDto;
import com.example.board.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {

    @Autowired
    private final CommentsService commentsService;

    // 생성
    @PostMapping("/comments/list/{id}")
    public Long create(@PathVariable Long id, String memberName, @RequestBody CommentRequestDto commentRequestDto){
        return commentsService.createComments(id,memberName,commentRequestDto);
    }

    // 조회
    @GetMapping("/comments/list/{id}")
    public List<CommentResponseDto> read(@PathVariable Long id){
        return commentsService.readComments(id);
    }

    // 수정
    @PutMapping("/comments/list/{id}")
    public void update(@PathVariable Long id){

    }

    // 삭제
    @DeleteMapping("comments/list/{id}")
    public void delete(@PathVariable Long id){

    }
}
