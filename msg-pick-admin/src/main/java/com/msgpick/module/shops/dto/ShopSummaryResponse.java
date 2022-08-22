package com.msgpick.module.shops.dto;

import com.msgpick.msgpick.code.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShopSummaryResponse {
    private Long shopId;
    private Long partnerId;
    private Status status;
}
