package com.challenge.challenge.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rewards_id_gen")
    @SequenceGenerator(name = "rewards_id_gen", sequenceName = "rewards_reward_id_seq", allocationSize = 1)
    @Column(name = "reward_id", nullable = false)
    private Long id;


    @Column(name = "reward_weight")
    private Long rewardWeight;

    @Size(max = 255)
    @Column(name = "reward_description")
    private String rewardDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Builder
    public Reward(Long id, Challenge challenge, String rewardDescription, Long rewardWeight) {
        this.id = id;
        this.challenge = challenge;
        this.rewardDescription = rewardDescription;
        this.rewardWeight = rewardWeight;
    }
}