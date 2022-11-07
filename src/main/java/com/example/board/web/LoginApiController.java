package com.example.board.web;

import com.example.board.service.login.LoginService;
import com.example.board.web.dto.LoginDto;
import com.example.board.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody MemberRequestDto memberRequestDto){
        return loginService.signUp(memberRequestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }
}
