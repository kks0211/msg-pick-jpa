package com.msgpick.module.partners.dto;

import com.msgpick.module.partners.domain.Partner;
import com.msgpick.msgpick.code.PartnerRole;

import java.time.LocalDateTime;

public record PartnerDto(LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, Long partnerId,
                         String email, String phone, String password, PartnerRole role) {

    public static PartnerDto of(LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, Long partnerId, String email, String phone, String password, PartnerRole role) {
        return new PartnerDto(createdAt, updatedAt, deleted, partnerId, email, phone, password, role);
    }

    public static PartnerDto of(String email, String phone, String password) {
        return new PartnerDto(null, null, null, null, email, phone, password, null);
    }

    public Partner toEntity() {
        return Partner.of(email, phone, password);
    }

    public static PartnerDto from(Partner entity) {
        return new PartnerDto(
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isDeleted(),
                entity.getPartnerId(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPassword(),
                entity.getRole());
    }
}
