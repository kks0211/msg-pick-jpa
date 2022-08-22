package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    SHOP("마사지샵", 1),
    HOMECARE("홈케어", 2);

    private final String description;
    private final int order;

}
