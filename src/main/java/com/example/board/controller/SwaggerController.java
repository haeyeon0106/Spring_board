package com.example.board.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "스웨거 Redirect")
@Controller
public class SwaggerController {
    @GetMapping("/api/board")
    public String redirectSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
