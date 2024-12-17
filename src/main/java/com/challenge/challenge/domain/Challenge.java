package com.challenge.challenge.domain;

import com.challenge.challenge.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenges_id_gen")
    @SequenceGenerator(name = "challenges_id_gen", sequenceName = "challenges_challenge_id_seq", allocationSize = 1)
    @Column(name = "challenge_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Setter
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "category")
    private Category category;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "participants")
    private Integer participants;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Challenge(Long id,
                     String title,
                     String description,
                     Integer participants,
                     Category category,
                     LocalDate startDate,
                     LocalDate endDate,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.participants = participants;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}