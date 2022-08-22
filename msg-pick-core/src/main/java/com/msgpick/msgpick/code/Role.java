package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("관리자", 1),
    MANAGER("매니저", 2);

    private final String description;
    private final int order;

}
