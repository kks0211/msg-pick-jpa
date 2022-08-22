package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    서비스시간
 */
@Getter
@RequiredArgsConstructor
public enum ServiceTime {

    DEFAULT("서비스시간은은 입실부터 퇴실시간입니다.", 1),
    MASSAGE_ONLY("서비스시간은 순수 마사지시간(순수관리시간)입니다.", 2),
    ENTER_THERAPIST("서비스시간은 관리사 입실 시간부터 시작입니다.", 3)
    ;

    private final String description;
    private final int order;

}
