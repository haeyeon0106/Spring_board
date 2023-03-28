package com.example.board.controller;

import com.example.board.dto.CommentsRequestDto;
import com.example.board.dto.CommentsResponseDto;
import com.example.board.dto.CommentsUpdateRequestDto;
import com.example.board.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "댓글 API")
@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @PostMapping("/api/v1/posts/{id}/comments")
    public Long save(@PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto){
        return commentService.saveComment(id,commentsRequestDto);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/api/v1/posts/{id}/comments/{commentId}")
    public CommentsResponseDto findById(@PathVariable Long id, @PathVariable Long commentId){
        return commentService.findByCommentId(id,commentId);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/api/v1/posts/{id}/comments/{commentId}")
    public String update(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentsUpdateRequestDto updateRequestDto){
        return commentService.updateComment(id,commentId,updateRequestDto);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/api/v1/posts/{id}/comments/{commentId}")
    public String delete(@PathVariable Long id, @PathVariable Long commentId){
        return commentService.deleteComment(id,commentId);
    }
}
