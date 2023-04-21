package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Comments extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"posts"})    // 무한참조 방지
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "members_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comments parent;

    @Column
    private Integer depth;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Comments> child = new ArrayList<>();

    public void update(String contents){this.contents = contents;}

    // 부모 댓글 수정
    public void updateParent(Comments parent){
        this.parent = parent;
    }

}
