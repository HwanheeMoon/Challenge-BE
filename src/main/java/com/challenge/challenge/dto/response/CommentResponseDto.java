package com.challenge.challenge.dto.response;

import com.challenge.challenge.domain.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

public record CommentResponseDto (
        Long challengeId,
        String content,
        LocalDateTime writeDate
) {


    @Builder
    public CommentResponseDto {

    }

    public static CommentResponseDto toEntity(Comment comment) {
        return CommentResponseDto
                .builder()
                .challengeId(comment.getChallenge().getId())
                .content(comment.getContent())
                .writeDate(comment.getCreatedAt())
                .build();
    }


}
