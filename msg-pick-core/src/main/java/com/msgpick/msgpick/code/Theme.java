package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Theme {

    AROMA("아로마", 1),
    SWEDISH("스웨디시", 2);

    private final String description;
    private final int order;

}
