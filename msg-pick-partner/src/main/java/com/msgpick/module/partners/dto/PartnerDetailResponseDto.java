package com.msgpick.module.partners.dto;

import com.msgpick.module.partners.domain.Partner;
import com.msgpick.msgpick.code.PartnerRole;

import java.time.LocalDateTime;

public record PartnerDetailResponseDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean deleted,
        Long partnerId,
        String email,
        String phone,
        PartnerRole role
) {

    public static PartnerDetailResponseDto from(Partner partner) {
        return new PartnerDetailResponseDto(partner.getCreatedAt(),
                partner.getUpdatedAt(),
                partner.isDeleted(),
                partner.getPartnerId(),
                partner.getPhone(),
                partner.getEmail(),
                partner.getRole());
    }
}
