package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "Text",nullable = false)
    private String contents;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();


//    @Builder
//    public Posts(String title,String contents,String author){
//        this.title = title;
//        this.contents = contents;
//        this.author = author;
//    }

    public void update(String title,String contents){
        this.title = title;
        this.contents = contents;
    }
}
