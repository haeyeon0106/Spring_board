package com.example.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    // 웹API 테스트할 떄 사용

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))            // MockMvc를 통해 HTTP GET 요청
                .andExpect(status().isOk())             // mvc.perform()의 결과 검증(HTTP Header의 Status 검증 -> 200,400 등 검증)
                .andExpect(content().string(hello));    // 반환하는 값이 hello가 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name",is(name))).andExpect(jsonPath("$.amount",is(amount)));
    }
}