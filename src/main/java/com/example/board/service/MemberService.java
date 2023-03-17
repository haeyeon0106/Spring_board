package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.dto.MemberRequestDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.MemberUpdateDto;
import com.example.board.exception.custom.InfoException;
import com.example.board.exception.error.ErrorCode;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;
//    private PasswordEncoder passwordEncoder;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /*
    * 회원가입
    * */
    @Transactional
    public Long signup(MemberRequestDto memberRequestDto){
//        validateMember(memberRequestDto);
        boolean existId = memberRepository.existsByMemberId(memberRequestDto.getMemberId());
        if(existId){
            throw new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"이미 존재하는 아이디입니다.");
        }

        return memberRepository.save(memberRequestDto.toEntity()).getId();
    }

//    private void validateMember(MemberRequestDto memberRequestDto) {        // 중복회원가입 검증
//        memberRepository.findByMemberId(memberRequestDto.getMemberId()).ifPresent(m->{
//            throw new RuntimeException("이미 가입된 회원입니다.");
//        });
//    }

    /*
    * 로그인
    * */

    @Transactional
    public MemberResponseDto login(MemberRequestDto memberRequestDto){
        Member entity = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        // 아이디 비번 일치한 지 확인
        if(entity == null){
            throw new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"아이디가 일치하지 않습니다.");
        } else if (!passwordEncoder.matches(memberRequestDto.getMemberPw(),entity.getMemberPw())) { //(암호화 전 비번, 암호화(db저장)된 비번)
            throw new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"비밀번호가 일치하지 않습니다.");
        }

        return new MemberResponseDto(entity);
    }

    /*
    * 이름 수정
    * */
    @Transactional
    public String changeName(Long id, MemberUpdateDto memberUpdateDto){
        Member member = memberRepository.findById(id).orElseThrow(()->new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"일치하는 회원이 없습니다."));
        member.updateName(memberUpdateDto.getName());
        return "이름 변경 성공";
    }

    /*
    * findBymemberId로 찾기
    * */
    @Transactional
    public MemberResponseDto findByMemberId(String memberId){
        Member entity = memberRepository.findByMemberId(memberId);
//                .orElseThrow(()->new RuntimeException("존재하지 않는 아이디입니다"));
        if (entity == null){throw new InfoException(ErrorCode.INTERNAL_SERVER_ERROR,"존재하지 않는 아이디입니다.");}
        return new MemberResponseDto(entity);
    }

}
