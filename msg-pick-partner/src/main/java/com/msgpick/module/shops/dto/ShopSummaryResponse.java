package com.msgpick.module.shops.dto;

import com.msgpick.msgpick.code.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopSummaryResponse {
    private Long shopId;
    private Long partnerId;
    private Status status;
    private String rejectMessage;
}
