package com.example.board.web;

import com.example.board.domain.login.Member;
import com.example.board.domain.login.MemberRepository;
import com.example.board.service.login.LoginService;
import com.example.board.web.dto.LoginDto;
import com.example.board.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public String signUp(@RequestBody MemberRequestDto memberRequestDto){
        return loginService.signUp(memberRequestDto);
    }

    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpServletRequest request,HttpServletResponse response){

        Member memberEntity = memberRepository.findByMemberId(loginDto.getMemberId());

        if(memberEntity == null){
            throw new RuntimeException("아이디 또는 비밀번호가 존재하지 않습니다");
        }else if(!passwordEncoder.matches(loginDto.getMemberPw(), memberEntity.getMemberPw())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공
        return loginService.login(loginDto.getMemberId(),response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        loginService.logout(request);
    }
}
