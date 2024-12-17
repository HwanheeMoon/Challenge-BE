package com.challenge.challenge.service;


import com.challenge.challenge.Exception.CommonException;
import com.challenge.challenge.Exception.ErrorCode;
import com.challenge.challenge.domain.Challenge;
import com.challenge.challenge.domain.Comment;
import com.challenge.challenge.dto.response.CommentResponseDto;
import com.challenge.challenge.repository.ChallengeRepository;
import com.challenge.challenge.repository.CommentRepository;
import com.challenge.challenge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    private final int contentsPerPage = 10;

    @Transactional
    public Boolean create(Long userId, Long challengeId, String content) {
        commentRepository.save(Comment
                .builder()
                        .user(userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE)))
                        .challenge(challengeRepository.findById(challengeId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE)))
                        .content(content)
                        .createdAt(LocalDateTime.now())
                .build());

        return true;
    }

    @Transactional
    public Page<CommentResponseDto> getCommentListByChallengeId(Long challengeId, int page) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        return commentRepository
                .findByChallenge(
                        PageRequest.of(page, contentsPerPage), challenge
                ).map(CommentResponseDto::toEntity);
    }

    @Transactional
    public Boolean delete(Long commentId) {
        commentRepository.deleteById(commentId);

        return true;
    }







}
