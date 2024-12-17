package com.challenge.challenge.repository;

import com.challenge.challenge.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Optional<Challenge> findById(Long id);

    @Query("UPDATE Challenge c SET c.description = :description WHERE c.id = :id")
    void modifyDescription(@Param("id") Long id, @Param("description") String description);

    @Query("UPDATE Challenge SET title = :title WHERE id = :id")
    void modifyTitle(@Param("id") Long id, @Param("title") String title);

    Page<Challenge> findAll(Pageable pageable);


}
