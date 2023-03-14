package com.example.board.controller;

import com.example.board.domain.Posts;
import com.example.board.dto.PostUpdateRequestDto;
import com.example.board.dto.PostsSaveRequestDto;
import com.example.board.repository.PostsRepository;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//랜덤포트를 사용하는 이유 : 포트 충돌 방지를 위해 여러 개의 테스트 코드 작성 시 랜덤포트 사용
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void teardown(){postsRepository.deleteAll();}

    @Test
    void Posts_등록된다() throws Exception {
        // given
        String title = "스프링 개발자";
        String contents = "꼭 스프링 천재가 되어야지";

        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .title(title)
                .contents(contents)
                .author("익명의 누군가")
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,postsSaveRequestDto,Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
    }

    @Test
    void 게시글_수정하기() {
        // given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .contents("contents")
                .author("author")
                .build());

        Long updateId = savePosts.getId();
        String updateTitle = "title2";
        String updateContents = "contents2";

        PostUpdateRequestDto updateRequestDto = PostUpdateRequestDto.builder()
                .title(updateTitle)
                .contents(updateContents)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts"+updateId;

        // when

        // then
    }

    @Test
    void findById() {
    }
}