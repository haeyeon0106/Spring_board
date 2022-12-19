package com.example.board.service.comments;

import com.example.board.domain.comments.Comments;
import com.example.board.domain.comments.CommentsRepository;
import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.error.exception.UserNotFoundException;
import com.example.board.web.dto.CommentDto.CommentRequestDto;
import com.example.board.web.dto.CommentDto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;

    // 댓글 생성
    @Transactional
    public Long createComments(Long id, String memberName, CommentRequestDto commentRequestDto){
        Member member = memberRepository.findByMemberName(memberName);
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."+id));

        commentRequestDto.setMember(member);
        commentRequestDto.setPosts(posts);

        Comments comments = commentRequestDto.toEntity();
        commentsRepository.save(comments);

        return comments.getId();
    }

    // 댓글 읽기
    @Transactional
    public List<CommentResponseDto> readComments(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."+id));
        List<Comments> comments = posts.getComments();

        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    public void updateComments(Long id, CommentRequestDto commentRequestDto){
            Comments comments = commentsRepository.findById(id)
                    .orElseThrow(()->new IllegalArgumentException("해당 사용자가 존재하지 않습니다."+id));

            comments.update(commentRequestDto.getComment());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComments(Long id){
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 존재하지 않습니다."+id));

        commentsRepository.delete(comments);
    }
}
