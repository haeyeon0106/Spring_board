package com.example.board.controller;

import com.example.board.dto.PostUpdateRequestDto;
import com.example.board.dto.PostsResponseDto;
import com.example.board.dto.PostsSaveRequestDto;
import com.example.board.service.PostsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시판 API")
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @Operation(summary = "글 등록")
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto){
        return postsService.save(postsSaveRequestDto);
    }

    @Operation(summary = "글 수정")
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        return postsService.update(id,postUpdateRequestDto);
    }

    @Operation(summary = "글 찾기")
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @Operation(summary = "글 삭제")
    @DeleteMapping("/api/v1/posts/{id}")
    public String deletePost(@PathVariable Long id){return postsService.deletePost(id);}
}
