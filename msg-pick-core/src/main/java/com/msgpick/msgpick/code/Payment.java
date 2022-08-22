package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Payment {

    PRE_CASH("현금 선결제", 1),
    PRE_CASH_CARD("현금+카드 선결제", 2),
    ETC("기타", 3);

    private final String description;
    private final int order;

}
