package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayOff {

    SAT_SUN("토·일", 1),
    SAT("토", 2),
    SUN("일", 3),
    EVERYDAY("연중무휴", 4),
    PHONE_OFF("휴대폰이 꺼진 경우", 5);

    private final String description;
    private final int order;

}
