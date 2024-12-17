package com.challenge.challenge.dto.response;

import com.challenge.challenge.domain.Challenge;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public record ChallengeDetailResponseDto(String title,
                                         Integer participants,
                                         LocalDate startDate,
                                         LocalDate endDate) {

    @Builder
    public ChallengeDetailResponseDto {
    }

    public static ChallengeDetailResponseDto toEntity(Challenge challenge) {
        return ChallengeDetailResponseDto.builder()
                .title(challenge.getTitle())
                .participants(challenge.getParticipants())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .build();
    }
}
