package com.challenge.challenge.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    LIFESTYLE("생활습관"),
    EXERCISE("운동"),
    CARE("돌봄"),
    STUDY("공부");

    private final String category;

}
