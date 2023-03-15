package com.example.board.repository;

import com.example.board.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @AfterEach
    public void after(){
        memberRepository.deleteAll();
    }

    // 회원저장
    @Test
    public void 회원저장(){
        // given
        Member member = Member.builder().memberId("hello").memberPw("1234").name("hy").build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findByMemberId(saveMember.getMemberId());
//                .orElseThrow(()->new RuntimeException("저장된 회원이 없습니다"));

        if (findMember == null){throw new RuntimeException("저장된 회원이 없습니다");}
        Assertions.assertEquals(findMember,saveMember);
        Assertions.assertEquals(findMember,member);
    }


    // 회원 저장 시 아이디가 없으면 오류
    @Test
    public void 아이디_저장안함(){
        // given
        Member member = Member.builder().memberPw("1234").name("HY").build();

        // when, then
        assertThrows(Exception.class,()->memberRepository.save(member));
    }

    // existByMemberId
    @Test
    public void exist_memberId(){
        // given
        String memberId = "hello";
        Member member = Member.builder().memberId("hello").memberPw("1234").name("hy").build();
        Member saveMember = memberRepository.save(member);

        // when
        boolean existMember = memberRepository.existsByMemberId(memberId);
        boolean existMember2 = memberRepository.existsByMemberId(memberId+"123");
        // then
        assertEquals(true,existMember);
        assertEquals(false,existMember2);
    }


    // memberId로 회원 찾기 기능
    @Test
    public void 회원찾기(){
        // given
        String memberId = "hello";
        Member member = Member.builder().memberId("hello").memberPw("1234").name("hy").build();
        memberRepository.save(member);


        // when
        Member memberInfo = memberRepository.findByMemberId(memberId);

        // then
        assertEquals(member.getMemberId(),memberInfo.getMemberId());
        assertEquals(member.getName(),memberInfo.getName());
    }

    // 가입 시 생성시간 잘 등록
    @Test
    public void 회원가입시_생성시간_등록() throws Exception {
        //given
        Member member = Member.builder().memberId("hello").memberPw("1234").name("hy").build();
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findByMemberId(member.getMemberId());
//                .orElseThrow(() -> new Exception());

        if(findMember == null){throw new RuntimeException("");}
        //then
        assertThat(findMember.getCreatedDate()).isNotNull();
        assertThat(findMember.getLastModifiedDate()).isNotNull();

    }
}