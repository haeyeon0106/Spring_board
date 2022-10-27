//package com.example.board.web;
//
//import com.example.board.domain.posts.PostsRepository;
//import com.example.board.web.dto.PostsSaveRequestDto;
//import org.junit.After;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostsApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @After
//    public void tearDown() throws Exception{
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void posts_등록된다() throws Exception{
//        String title= "title";
//        String content = "content";
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author("author").build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts";
//
//
//    }
//
//}
