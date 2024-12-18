package com.challenge.challenge.service;


import com.challenge.challenge.Exception.CommonException;
import com.challenge.challenge.Exception.ErrorCode;
import com.challenge.challenge.domain.Challenge;
import com.challenge.challenge.dto.response.ChallengeDetailResponseDto;
import com.challenge.challenge.dto.response.ChallengeSimpleResponseDto;
import com.challenge.challenge.enums.Category;
import com.challenge.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
                .img(challenge.getImg())
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


    /**
     * 카테고리 별 리스트
     * @param pageable 페이징 객체
     * @param category 카테고리 객체
     * @return DTO 리스트 반환
     */
    @Transactional
    protected Page<ChallengeSimpleResponseDto> getChallenges(Pageable pageable, String category) {

        if(category.equals("ALL")) {
            return challengeRepository.findAll(pageable)
                    .map(ChallengeSimpleResponseDto::toEntity);
        } else {
            return challengeRepository.findAllByCategory(pageable, category)
                    .map(ChallengeSimpleResponseDto::toEntity);
        }

    }

    @Transactional
    public Page<ChallengeSimpleResponseDto> getAllChallenges(String tab, String category, int page) {

        switch (tab) {
            case "best" -> {
                Pageable pageable = PageRequest.of(
                        page,
                        contentsPerPage,
                        Sort.Direction.DESC,
                        "participants");

                return getChallenges(pageable, category);
            }
            case "new" -> {
                Pageable pageable = PageRequest.of(
                        page,
                        contentsPerPage);

                return getChallenges(pageable, category);
            }
            case "recommend" -> {
                Pageable pageable = PageRequest.of(
                        page,
                        contentsPerPage,
                        Sort.Direction.DESC,
                        "");
                return getChallenges(pageable, category);
            }
            default -> throw new CommonException(ErrorCode.METHOD_NOT_ALLOWED);
        }
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
