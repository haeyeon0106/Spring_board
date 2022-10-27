package com.example.board.web;

import com.example.board.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/posts/login")
    public void login( ){}

    @GetMapping("/posts/logout")
    public void logout(){}
}
