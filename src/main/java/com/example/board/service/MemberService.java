package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.MemberUpdateDto;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    /*
    * 회원가입
    * */
    @Transactional
    public Long signup(MemberRequestDto memberRequestDto){
        validateMember(memberRequestDto);
        Member member = Member.builder().memberId(memberRequestDto.getMemberId())
                .memberPw(memberRequestDto.getMemberPw())
                .name(memberRequestDto.getName())
                .build();

        return memberRepository.save(member).getId();
    }

    private void validateMember(MemberRequestDto memberRequestDto) {        // 중복회원가입 검증
        memberRepository.findByMemberId(memberRequestDto.getMemberId()).ifPresent(m->{
            throw new RuntimeException("이미 가입된 회원입니다.");
        });
    }

    /*
    * 로그인
    * */

    @Transactional
    public Long login(MemberRequestDto memberRequestDto){
        Optional<Member> entity = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        // 아이디 비번 일치한 지 확인
        if(entity == null){
            throw new RuntimeException("아이디가 일치하지 않습니다.");
        } else if (!passwordEncoder.matches(entity.get().getMemberPw(), memberRequestDto.getMemberPw())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return entity.get().getId();
    }

    /*
    * 이름 수정
    * */
    @Transactional
    public String changeName(Long id, MemberUpdateDto memberUpdateDto){
        Member member = memberRepository.findById(id).orElseThrow(()->new RuntimeException("일치하는 회원이 없습니다."));
        member.updateName(memberUpdateDto.getName());
        return "이름 변경 성공";
    }

    /*
    * findBymemberId로 찾기
    * */
    @Transactional
    public MemberResponseDto findByMemberId(String memberId){
        Member entity = memberRepository.findByMemberId(memberId).orElseThrow(()->new RuntimeException("존재하지 않는 아이디입니다"));
        return new MemberResponseDto(entity);
    }

}
