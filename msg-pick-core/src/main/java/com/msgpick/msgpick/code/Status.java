package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    REVIEW("입점 심사중", 1),
    APPROVAL("입점 승인", 2),
    REJECT("입점 거절", 3);

    private final String description;
    private final int order;
}
