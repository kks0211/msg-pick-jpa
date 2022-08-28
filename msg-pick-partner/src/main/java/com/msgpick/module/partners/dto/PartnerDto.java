package com.msgpick.module.partners.dto;

import com.msgpick.module.partners.domain.Partner;
import com.msgpick.msgpick.code.PartnerRole;

import java.time.LocalDateTime;

public record PartnerDto(
        Long id,
        String phone,
        String email,
        String password,
        PartnerRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean deleted
) {

    public static PartnerDto of(String phone, String email, String password) {
        return new PartnerDto(null, phone, email, password, null, null, null, null);
    }

    public static PartnerDto from(Partner entity) {
        return new PartnerDto(
                entity.getId(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeleted()
        );
    }

    public static PartnerDetailResponse of(Partner entity) {
        return PartnerDetailResponse.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deleted(entity.getDeleted())
                .build();
    }

    public Partner toEntity() {
        return Partner.builder()
                .email(email)
                .phone(phone)
                .password(password)
                .build();
    }

}
