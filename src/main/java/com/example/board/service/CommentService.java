package com.example.board.service;

import com.example.board.domain.Comments;
import com.example.board.domain.Posts;
import com.example.board.dto.CommentsRequestDto;
import com.example.board.dto.CommentsResponseDto;
import com.example.board.dto.CommentsUpdateRequestDto;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long saveComment(Long id, CommentsRequestDto commentsRequestDto){
//        Member member = memberRepository.findByMemberId(memberId);
//        if(member == null){throw new RuntimeException("회원정보가 없습니다.");}
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        CommentsRequestDto dto = CommentsRequestDto.builder()
                .contents(commentsRequestDto.getContents())
//                .member(member)
                .posts(posts).build();

        return commentRepository.save(dto.toEntity()).getId();
    }


//    @Transactional
//    public CommentsResponseDto findById(Long id){
//        Posts posts = postsRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
//    }

    @Transactional
    public String updateComment(Long id, Long commentId, CommentsUpdateRequestDto updateRequestDto){
        postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id: "+id));
        comments.update(updateRequestDto.getContents());
        return "댓글 수정 완료";
    }
    @Transactional
    public CommentsResponseDto findByCommentId(Long id,Long commentId){
        postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id: " + id));

        return new CommentsResponseDto(comments);
    }

    @Transactional
    public String deleteComment(Long id, Long commentId){
        postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id: " + id));

        commentRepository.delete(comments);
        return "댓글 삭제 완료";
    }
}
