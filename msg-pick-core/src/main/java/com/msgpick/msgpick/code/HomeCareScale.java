package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HomeCareScale {

    ONE_PERSON("1인 홈케어", 1),
    TWO_PERSON("2인 홈케어", 2);

    private final String description;
    private final int order;

}
