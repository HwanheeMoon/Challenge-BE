package com.challenge.challenge.dto.response;

import com.challenge.challenge.domain.User;
import lombok.Builder;
import lombok.Getter;

public record MyPageResponseDto (
        String name,
        Long cash,
        Long reward,
        Integer participating,
        Integer completed,
        Integer chargePer
) {


    @Builder
    public MyPageResponseDto {

    }

}
