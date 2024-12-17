package com.challenge.challenge.repository;

import com.challenge.challenge.domain.Participation;
import com.challenge.challenge.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    @Query(value = "select Participation from Participation p", nativeQuery = false)
    List<Participation> findAll();

    @Modifying
    @Query("update Participation set status = :status where id = :id")
    void modifyStatus(@Param("id") Long id, @Param("status") String status);

    @EntityGraph(attributePaths = {"user"})
    @Query("select Participation from Participation where user.id = :id")
    List<Participation> findByUserId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"challenge"})
    @Query("select Participation from Participation where challenge.id = :id")
    List<Participation> findByChallengeId(@Param("id") Long id);

    @Query("select count(*) from Participation p where p.user = :user")
    Integer findCntByUser(User user);



}
