package com.example.board.web;

import com.example.board.service.login.LoginService;
import com.example.board.web.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginDto){
        return loginService.login(loginDto);
    }

}
