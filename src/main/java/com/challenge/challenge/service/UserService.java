package com.challenge.challenge.service;


import com.challenge.challenge.Exception.CommonException;
import com.challenge.challenge.Exception.ErrorCode;
import com.challenge.challenge.domain.User;
import com.challenge.challenge.dto.response.MyPageResponseDto;
import com.challenge.challenge.repository.ParticipationRepository;
import com.challenge.challenge.repository.ResultRepository;
import com.challenge.challenge.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final ResultRepository resultRepository;

    private final int totalChallenges = 100;

    @Transactional
    public MyPageResponseDto getMyPage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new CommonException(ErrorCode.NOT_FOUND_RESOURCE)
        );

        Integer nowParticipating = participationRepository.findCntByUser(user);

        Integer nowCompleted = resultRepository.findByUser(user).size();

        Integer chargePercentage = (nowCompleted / totalChallenges) * 100;

        return MyPageResponseDto
                .builder()
                .completed(nowCompleted)
                .cash(user.getCash())
                .chargePer(chargePercentage)
                .name(user.getUsername())
                .participating(nowParticipating)
                .reward(user.getReward())
                .build();
    }



}
