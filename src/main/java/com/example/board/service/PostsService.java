package com.example.board.service;

import com.example.board.domain.Posts;
import com.example.board.dto.PostListResponseDto;
import com.example.board.dto.PostUpdateRequestDto;
import com.example.board.dto.PostsResponseDto;
import com.example.board.dto.PostsSaveRequestDto;
import com.example.board.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

   private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto){
        return  postsRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto postUpdateRequestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id: "+id));
        posts.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContents());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id: "+id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public String deletePost(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."+id));
        postsRepository.delete(entity);
        return "게시글 삭제 완료";
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
