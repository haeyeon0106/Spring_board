package com.example.board.service;

import com.example.board.dto.MemberRequestDto;
import com.example.board.repository.MemberRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class passwordEncodeTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void passwordEncode(){

        // given
        String memberId = "hello";
        String memberPw = "1234";

        MemberRequestDto requestDto = MemberRequestDto.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .name("hy").build();

        // when
        memberRepository.save(requestDto.toEntity());

        String encodedPw = memberRepository.findByMemberId(memberId).getMemberPw();

        // then
        assertThat(memberPw).isNotEqualTo(encodedPw);
        Assertions.assertAll(()->passwordEncoder.matches(memberPw,encodedPw));
    }
    
}
