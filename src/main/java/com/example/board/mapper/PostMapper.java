package com.example.board.mapper;

import com.example.board.domain.Posts;
import com.example.board.dto.CommentsResponseDto;
import com.example.board.dto.PostsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "posts.id",target ="id")
    @Mapping(source = "posts.title",target = "title")
    @Mapping(source = "posts.contents",target = "contents")
    @Mapping(source = "posts.createdAt",target = "createdAt")
    @Mapping(source = "posts.updatedAt",target = "updatedAt")
    PostsResponseDto toPostResponseDto(Posts posts);
}
