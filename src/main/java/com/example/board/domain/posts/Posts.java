package com.example.board.domain.posts;

import com.example.board.domain.comments.Comments;
import com.example.board.domain.login.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter             // Lombok 어노테이션
@NoArgsConstructor  // Lombok 어노테이션
@AllArgsConstructor
@Entity     // JPA 어노테이션(테이블과 링크)
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column(name = "created_Date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_Date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩(Member를 사용하는 시점에 쿼리가 나가도록 함)
    @JoinColumn(name = "member_id")
    private Member member;

    // Comments의 @ManyToOne과 Posts의 @OneToMany로 양방향 관계 생성
    // CascadeType.REMOVE -> 게시글 삭제 시 댓글까지 삭제
    @OneToMany(mappedBy = "posts",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")  //댓글 정렬
    private List<Comments> comments;

//    @Builder
//    public Posts(String title, String content, String author){
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    }

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }

}
