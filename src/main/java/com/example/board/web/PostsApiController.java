package com.example.board.web;

import com.example.board.service.posts.PostsService;
import com.example.board.web.dto.PostDto.PostsResponseDto;
import com.example.board.web.dto.PostDto.PostsSaveRequestDto;
import com.example.board.web.dto.PostDto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public  Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    // 수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    // 조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findId(@PathVariable Long id){
        return postsService.findById(id);
    }
}
