package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    서비스대상
 */
@Getter
@RequiredArgsConstructor
public enum ServiceTarget {

    MALE_ONLY("남성전용", 1),
    FEMALE_ONLY("여성전용", 2),
    COMMON("남녀공용", 3),
    COMMON_AND_COUPLE("남녀공용 및 커플전용", 4);

    private final String description;
    private final int order;

}
