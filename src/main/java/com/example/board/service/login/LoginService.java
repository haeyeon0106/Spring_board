package com.example.board.service.login;

import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.web.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private MemberRepository memberRepository;
    @Transactional
    public String login(@RequestBody LoginRequestDto requestDto){
        Member user = memberRepository.findByMemberId(requestDto.getMemberId());

        if (user.getMemberId().isEmpty()) {
            throw new RuntimeException("아이디 값이 존재하지 않습니다.");
        } else if (user.getMemberPw().equals(requestDto.getMemberPw())) {
            return "SUCCESS";
        }

        return "FAILED";
    }
}
