package com.example.board.service.login;

import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.web.dto.LoginDto;
import com.example.board.web.dto.MemberRequestDto;
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
    public String signUp(@RequestBody MemberRequestDto memberRequestDto){

        if (memberRepository.existsById(memberRequestDto.getMemberId())) {  // 아이디 중복확인
            throw new RuntimeException("중복된 아이디입니다.");
        } else {    // 중복되지 않은 아이디
            String encodePw = passwordEncoder.encode(memberRequestDto.getMemberPw());
            memberRequestDto.setMemberPw(encodePw);
            Member entity = memberRequestDto.toEntity();
            memberRepository.save(entity);

            return "SUCCESS";
        }
    }

    @Transactional
    public String login(@RequestBody LoginDto loginDto){

        Member memberEntity = memberRepository.findById(loginDto.getMemberId());

        if(memberEntity==null){
            throw  new RuntimeException("아이디가 존재하지 않습니다");
        }else if(!passwordEncoder.matches(loginDto.getMemberPw(),memberEntity.getMemberPw())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return "로그인 성공";
    }
}
