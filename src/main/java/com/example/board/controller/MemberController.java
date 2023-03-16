package com.example.board.controller;

import com.example.board.config.jwt.JwtProvider;
import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.MemberUpdateDto;
import com.example.board.dto.TokenDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    // 회원가입
    @PostMapping("/api/v1/signup")
    public Long signup(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.signup(memberRequestDto);
    }

    // 로그인
    @PostMapping("/api/v1/login")
    public TokenDto login(@RequestBody MemberRequestDto memberRequestDto){
        MemberResponseDto loginResponse = memberService.login(memberRequestDto);
        return jwtProvider.generateToken(loginResponse.getMemberId(),loginResponse.getName());
//        return memberService.login(memberRequestDto);
    }

    // 이름 수정
    @PutMapping("/api/v1/update/{id}")
    public String changeName(@PathVariable Long id, @RequestBody MemberUpdateDto memberUpdateDto){
        return memberService.changeName(id,memberUpdateDto);
    }

    // memberId로 찾기
    @GetMapping("/api/v1/find/{memberId}")
    public MemberResponseDto findByMemberId(@PathVariable String memberId){
        return memberService.findByMemberId(memberId);
    }


}
