package com.example.board.web;

import com.example.board.service.comments.CommentsService;
import com.example.board.web.dto.CommentDto.CommentRequestDto;
import com.example.board.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {

    @Autowired
    private final CommentsService commentsService;

    // 생성
    @PostMapping("/comments/list/{id}")
    public ResponseEntity create(@PathVariable Long id, String memberName, @RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentsService.createComments(id,memberName,commentRequestDto));
    }

    // 조회
    @GetMapping("/comments/list/{id}")
    public List<CommentResponseDto> read(@PathVariable Long id){
        return commentsService.readComments(id);
    }

    // 수정
    @PutMapping("/comments/list/{id}")
    public ResponseEntity update(@PathVariable Long id, CommentRequestDto commentRequestDto){
        commentsService.updateComments(id,commentRequestDto);
        return ResponseEntity.ok(id);
    }

    // 삭제
    @DeleteMapping("comments/list/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        commentsService.deleteComments(id);
        return ResponseEntity.ok(id);
    }
}
