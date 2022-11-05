package com.example.board.service.login;

import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder;


    @Transactional
    public String login(Member member,@RequestBody LoginDto loginDto){
        Member user = memberRepository.findByMemberId(loginDto.getMemberId());

        String encodePw = passwordEncoder.encode(member.getMemberPw());
        loginDto.setMemberPw(encodePw);
        memberRepository.save(member);

        if (user.getMemberId().isEmpty()) {
            throw new RuntimeException("아이디 값이 존재하지 않습니다.");
        } else if (passwordEncoder.matches(loginDto.getMemberPw(),user.getMemberPw())) {
            return "SUCCESS";
        }
        return "FAILED";
    }
}
