package com.example.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "members")
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false,length = 100)
    private String memberPw;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String memberId,String memberPw,String name){
        this.memberId = memberId;
        this.name = name;
        this.memberPw = memberPw;
    }

    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();

    public void updateName(String newName){
        this.name = newName;
    }
}
