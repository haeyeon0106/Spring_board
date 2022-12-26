package com.example.board.web;

import com.example.board.service.comments.CommentsService;
import com.example.board.web.dto.CommentDto.CommentRequestDto;
import com.example.board.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class CommentsApiController {

    private final CommentsService commentsService;

    // 생성
    @PostMapping("/list/{id}/comment")
    public ResponseEntity create(@PathVariable Long id, String memberName, @RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentsService.createComments(id,memberName,commentRequestDto));
    }

    // 조회
    @GetMapping("/list/{id}/comment")
    public List<CommentResponseDto> read(@PathVariable Long id){
        return commentsService.readComments(id);
    }

    // 수정
    @PutMapping("/list/{id}/comment")
    public ResponseEntity update(@PathVariable Long id, CommentRequestDto commentRequestDto){
        commentsService.updateComments(id,commentRequestDto);
        return ResponseEntity.ok(id);
    }

    // 삭제
    @DeleteMapping("/list/{id}/comment")
    public ResponseEntity delete(@PathVariable Long id){
        commentsService.deleteComments(id);
        return ResponseEntity.ok(id);
    }
}
