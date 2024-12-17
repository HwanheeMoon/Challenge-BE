package com.challenge.challenge.repository;


import com.challenge.challenge.domain.Result;
import com.challenge.challenge.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @EntityGraph(attributePaths = {"participation"})
    List<Result> findAll();

    @EntityGraph(attributePaths = {"participation"})
    @Query("select Result from Result r where r.participation.id = :id")
    List<Result> findByParticipationId(@Param("id") Long id);

    @Modifying
    @Query("update Result r set r.completionTime = :completionTime where r.id = :id ")
    void modifyCompletionTime(@Param("id") Long id, @Param("completionTime") Instant completionTime);

    List<Result> findByUser(User user);




}
