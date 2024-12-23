package com.challenge.challenge.dto.response;

import com.challenge.challenge.domain.Challenge;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public record ChallengeDetailResponseDto(
        Long id,
        String title,
        Integer participants,
        String img,
        LocalDate startDate,
        LocalDate endDate) {

    @Builder
    public ChallengeDetailResponseDto {
    }

    public static ChallengeDetailResponseDto toEntity(Challenge challenge) {
        return ChallengeDetailResponseDto.builder()
                .id(challenge.getId())
                .title(challenge.getTitle())
                .participants(challenge.getParticipants())
                .img(challenge.getImg())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .build();
    }
}
