package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.dto.MemberRequestDto;
import com.example.board.repository.MemberRepository;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @After
    public void teardown(){memberRepository.deleteAll();}

    @Test
    void 회원가입() {
        // given
        String memberId = "hello123";
        String name = "hy";
        String memberPw = "5555";

        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .memberId(memberId)
                .name(name)
                .memberPw(memberPw)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,memberRequestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> members = memberRepository.findAll();

        assertThat(members.get(0).getMemberId()).isEqualTo(memberId);
        assertThat(members.get(0).getName()).isEqualTo(name);
        assertThat(members.get(0).getMemberPw()).isEqualTo(memberPw);
    }
}