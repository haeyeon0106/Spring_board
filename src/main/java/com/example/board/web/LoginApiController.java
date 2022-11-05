package com.example.board.web;

import com.example.board.domain.login.Member;
import com.example.board.service.login.LoginService;
import com.example.board.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(Member member,@RequestBody LoginDto loginDto){
        return loginService.login(member,loginDto);
    }

}
