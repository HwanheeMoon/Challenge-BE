package com.challenge.challenge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_id_gen")
    @SequenceGenerator(name = "results_id_gen", sequenceName = "results_result_id_seq", allocationSize = 1)
    @Column(name = "result_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "participation_id", nullable = false)
    private Participation participation;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "completion_time")
    private Instant completionTime;

}