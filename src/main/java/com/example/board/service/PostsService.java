package com.example.board.service;

import com.example.board.domain.Posts;
import com.example.board.dto.PostUpdateRequestDto;
import com.example.board.dto.PostsResponseDto;
import com.example.board.dto.PostsSaveRequestDto;
import com.example.board.exception.custom.InfoException;
import com.example.board.exception.error.ErrorCode;
import com.example.board.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(()->new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"해당 게시글이 없습니다. id: "+id));
        posts.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContents());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"해당 게시글이 없습니다. id: "+id));
        return new PostsResponseDto(entity);
    }
}
