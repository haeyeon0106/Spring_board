package com.example.board.controller;

import com.example.board.dto.CommentsRequestDto;
import com.example.board.dto.CommentsResponseDto;
import com.example.board.dto.CommentsUpdateRequestDto;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/v1/posts/{id}/comments")
    public Long save(@PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto){
        return commentService.saveComment(id,commentsRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}/comments/{commentId}")
    public CommentsResponseDto findById(@PathVariable Long id, @PathVariable Long commentId){
        return commentService.findByCommentId(id,commentId);
    }

    @PutMapping("/api/v1/posts/{id}/comments/{commentId}")
    public String update(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentsUpdateRequestDto updateRequestDto){
        return commentService.updateComment(id,commentId,updateRequestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}/comments/{commentId}")
    public String delete(@PathVariable Long id, @PathVariable Long commentId){
        return commentService.deleteComment(id,commentId);
    }
}
