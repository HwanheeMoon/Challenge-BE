package com.challenge.challenge.dto.response;

import com.challenge.challenge.domain.Challenge;
import com.challenge.challenge.enums.Category;
import lombok.Builder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record ChallengeSimpleResponseDto(
        Long id,
        String title,
        Integer participants,
        Integer deadline,
        LocalDate startDate,
        Category category
) {

    @Builder
    public ChallengeSimpleResponseDto {

    }

    public static ChallengeSimpleResponseDto toEntity(Challenge challenge) {
        return ChallengeSimpleResponseDto.builder()
                .id(challenge.getId())
                .title(challenge.getTitle())
                .participants(challenge.getParticipants())
                .startDate(challenge.getStartDate())
                .deadline((int)( ChronoUnit.DAYS.between(LocalDate.now(), challenge.getEndDate())))
                .category(challenge.getCategory())
                .build();
    }
}
