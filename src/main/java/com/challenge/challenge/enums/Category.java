package com.challenge.challenge.enums;

import com.challenge.challenge.Exception.CommonException;
import com.challenge.challenge.Exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    LIFESTYLE("생활습관"),
    EXERCISE("운동"),
    CARE("돌봄"),
    STUDY("공부");

    private final String category;

    public Boolean equal(String category) {
        return this.category.equals(category);
    }

    public static Category toEntity(String category) {
        return switch (category) {
            case "LIFESTYLE" -> Category.LIFESTYLE;
            case "EXERCISE" -> Category.EXERCISE;
            case "CARE" -> Category.CARE;
            case "STUDY" -> Category.STUDY;
            default -> throw new CommonException(ErrorCode.DUPLICATE_RESOURCE);
        };
    }

}
