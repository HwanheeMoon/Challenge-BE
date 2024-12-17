package com.challenge.challenge.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participation_id_gen")
    @SequenceGenerator(name = "participation_id_gen", sequenceName = "participation_participation_id_seq", allocationSize = 1)
    @Column(name = "participation_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Size(max = 12)
    @Column(name = "status", length = 12)
    @Setter
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "joined_at")
    private Instant joinedAt;

    @Builder
    public Participation(Integer id,
                         User user,
                         Challenge challenge,
                         String status,
                         Instant joinedAt) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.status = status;
        this.joinedAt = joinedAt;
    }
}