package com.challenge.challenge.repository;


import com.challenge.challenge.domain.Challenge;
import com.challenge.challenge.domain.Comment;
import com.challenge.challenge.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();

    @Query("select Comment from Comment c where c.user.id = :id")
    List<Comment> findByUser(@Param("id") Long id);

    @Query("update Comment c SET c.content = :content WHERE c.id = :id")
    void modifyContent(@Param("id") Long id, @Param("content") String content);


    Page<Comment> findByChallenge(Pageable pageable, Challenge challenge);


}
