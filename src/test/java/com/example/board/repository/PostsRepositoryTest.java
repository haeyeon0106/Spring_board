package com.example.board.repository;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){postsRepository.deleteAll();}

    @Test
    public void 게시글저장_불러오기(){

//        // given
//        String title = "타이틀입니당";
//        String contents = "컨텐츠입니당";
//
//        postsRepository.save(Posts.builder().title(title).contents(contents).author("해연쓰").build());
//
//        // when
//        List<Posts> postList = postsRepository.findAll();
//
//        // then
//        Posts posts = postList.get(0);
//        Assertions.assertEquals(posts.getTitle(),title);
//        Assertions.assertEquals(posts.getContents(),contents);
    }
}