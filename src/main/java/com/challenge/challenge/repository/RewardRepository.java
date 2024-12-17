package com.challenge.challenge.repository;

import com.challenge.challenge.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {

    @Query("select Reward from Reward r where r.challenge.id = :id")
    List<Reward> findByChallengeId(@Param("id") Long id);

    @Modifying
    @Query("update Reward r set r.rewardDescription = :description where r.id = :id")
    void modifyDescription(@Param("id") Long id, @Param("description") String description);


}
