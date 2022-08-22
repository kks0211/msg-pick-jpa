package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Scale {

    ONE_PERSON("1인샵", 1),
    TWO_PERSON("2인샵", 2);

    private final String description;
    private final int order;

}
