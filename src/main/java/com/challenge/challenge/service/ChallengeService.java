package com.challenge.challenge.service;


import com.challenge.challenge.Exception.CommonException;
import com.challenge.challenge.Exception.ErrorCode;
import com.challenge.challenge.domain.Challenge;
import com.challenge.challenge.dto.response.ChallengeDetailResponseDto;
import com.challenge.challenge.dto.response.ChallengeSimpleResponseDto;
import com.challenge.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final int contentsPerPage = 6;

    public Boolean createChallenge(String title, String description, LocalDate startDate, LocalDate endDate) {
         challengeRepository.save(Challenge
                 .builder()
                         .title(title)
                         .description(description)
                         .startDate(startDate)
                         .endDate(endDate)
                         .createdAt(LocalDateTime.now())
                 .build());

         return true;
    }

    public ChallengeDetailResponseDto getChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return ChallengeDetailResponseDto.builder()
                .title(challenge.getTitle())
                .participants(challenge.getParticipants())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .build();
    }

    public Page<ChallengeDetailResponseDto> getAllChallenges(int page) {
        return challengeRepository
                .findAll(PageRequest.of(
                        page,
                        3,
                        Sort.Direction.DESC,
                        "endDate"
                )).map(ChallengeDetailResponseDto::toEntity);
    }

    public Page<ChallengeSimpleResponseDto> getAllBestChallenges(int page) {
        Pageable pageable = PageRequest.of(
                page,
                contentsPerPage,
                Sort.Direction.DESC,
                "participants");

        return challengeRepository
                .findAll(pageable)
                .map(ChallengeSimpleResponseDto::toEntity);
    }

    public Page<ChallengeSimpleResponseDto> getAllNewChallenges(int page) {
        Pageable pageable = PageRequest.of(
                page,
                contentsPerPage,
                Sort.Direction.DESC,
                "createdAt");
        return challengeRepository
                .findAll(pageable)
                .map(ChallengeSimpleResponseDto::toEntity);
    }


    //TODO ?
    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
    }


    public Boolean deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
        return true;
    }





}
