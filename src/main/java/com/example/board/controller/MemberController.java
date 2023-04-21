package com.example.board.controller;

import com.example.board.config.jwt.JwtProvider;
import com.example.board.dto.*;
import com.example.board.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = "회원 API")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    // 회원가입
    @Operation(summary = "회원가입")
    @PostMapping("/api/v1/signup")
    public Long signup(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.signup(memberRequestDto);
    }

    // 로그인
    @Operation(summary = "로그인")
    @PostMapping("/api/v1/login")
    public TokenDto login(@RequestBody LoginDto loginDto){
//        MemberResponseDto loginResponse = memberService.login(loginDto);
//        return jwtProvider.generateToken(loginResponse.getMemberId(),loginResponse.getName());
        return memberService.login(loginDto);
    }

    // 이름 수정
    @Operation(summary = "이름 수정")
    @PutMapping("/api/v1/update/{id}")
    public String changeName(@PathVariable Long id, @RequestBody MemberUpdateDto memberUpdateDto){
        return memberService.changeName(id,memberUpdateDto);
    }

    // memberId로 찾기
    @Operation(summary = "아이디로 회원 찾기")
    @GetMapping("/api/v1/find/{memberId}")
    public MemberResponseDto findByMemberId(@PathVariable String memberId){
        return memberService.findByMemberId(memberId);
    }

}
