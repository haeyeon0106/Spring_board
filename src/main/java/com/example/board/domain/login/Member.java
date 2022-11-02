package com.example.board.domain.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String memberId;

    @Column(length = 100, nullable = false)
    private String memberPw;

    @Builder
    public Member(String memberId, String memberPw){
        this.memberId = memberId;
        this.memberPw = memberPw;
    }


}
