package com.msgpick.msgpick.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PartnerRole {
    OWNER("ννΈλ", 1);

    private final String description;
    private final int order;

}
