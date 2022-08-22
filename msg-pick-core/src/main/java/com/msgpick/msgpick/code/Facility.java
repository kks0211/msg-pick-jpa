package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Facility {
    NON_STOP("24시간", 1),
    PARKING("주차", 2),
    WIFI("와이파이", 3);

    private final String description;
    private final int order;

}
