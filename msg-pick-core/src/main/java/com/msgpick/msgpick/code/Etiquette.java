package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    예약에티켓
 */
@Getter
@RequiredArgsConstructor
public enum Etiquette {

    CANCEL_10M("에약시간 10분 초과시 자동취소입니다.", 1),
    DEDUCT("예약시간에 늦은경우 초과시간만큼 서비스타임 차감됩니다.", 2)
    ;

    private final String description;
    private final int order;

}
