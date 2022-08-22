package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Nationality {
    KOREA("한국", 1),
    ETC("기타", 2);

    private final String description;
    private final int order;
}
