package com.msgpick.module.shops.dto.response;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.code.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopSummaryResponse {
    private Long shopId;
    private Long partnerId;
    private Status status;
    private String rejectMessage;

    @Builder
    public ShopSummaryResponse(Long shopId, Long partnerId, Status status, String rejectMessage) {
        this.shopId = shopId;
        this.partnerId = partnerId;
        this.status = status;
        this.rejectMessage = rejectMessage;
    }

    public static ShopSummaryResponse toDto(Shop entity) {
        return ShopSummaryResponse.builder()
                .shopId(entity.getId())
                .partnerId(entity.getPartnerId())
                .status(entity.getStatus())
                .rejectMessage(entity.getRejectMessage())
                .build();
    }

}
