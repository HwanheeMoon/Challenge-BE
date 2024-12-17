package com.challenge.challenge.service;

import com.challenge.challenge.repository.ChallengeRepository;
import com.challenge.challenge.repository.ParticipationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantService {

    private final ParticipationRepository participationRepository;
    private final ChallengeRepository challengeRepository;







}
