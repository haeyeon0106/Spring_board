package com.example.board.controller;

import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.MemberUpdateDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/api/v1/signup")
    public Long signup(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.signup(memberRequestDto);
    }

    // 로그인
    @PostMapping("/api/v1/login")
    public Long login(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.login(memberRequestDto);
    }

    // 이름 수정
    @PostMapping("/api/v1/changeName")
    public String changeName(Long id, MemberUpdateDto memberUpdateDto){
        return memberService.changeName(id,memberUpdateDto);
    }

    // memberId로 찾기
    @GetMapping("/api/v1/find/{memberId}")
    public MemberResponseDto findByMemberId(@PathVariable String memberId){
        return memberService.findByMemberId(memberId);
    }


}
