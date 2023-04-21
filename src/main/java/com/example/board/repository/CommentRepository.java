package com.example.board.repository;


import com.example.board.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {
    @Query("select c from Comments c where c.parent is null")
    List<Comments> findAll();
}
