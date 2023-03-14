package com.example.board.dto;

import com.example.board.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Setter
@Getter
public class MemberRequestDto {

    private String memberId;
    private String memberPw;
    private String name;

    @Builder
    public MemberRequestDto(String memberId,String memberPw,String name){
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.name = name;
    }
    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .memberPw(new BCryptPasswordEncoder().encode(memberPw))
                .name(name)
                .build();
    }
}
