package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    매너규정
 */
@Getter
@RequiredArgsConstructor
public enum Manner {

    CASE1("퇴폐, 불법서비스에 대한 예약은 불가합니다.", 1),
    CASE2("마사지 프로그램외 퇴페서비스요구는 불가합니다.", 2)
    ;

    private final String description;
    private final int order;

}
