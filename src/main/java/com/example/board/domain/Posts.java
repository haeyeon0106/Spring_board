package com.example.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "Text",nullable = false)
    private String contents;

    private String author;

    @Builder
    public Posts(String title,String contents,String author){
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public void update(String title,String contents){
        this.title = title;
        this.contents = contents;
    }
}
