package com.msgpick.module.auth.dto;

import com.msgpick.module.auth.domain.Auth;

import java.time.LocalDateTime;

public record AuthDto(LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String phone,
                      LocalDateTime expiredAt, int verificationCode, LocalDateTime verifiedAt) {


    public static AuthDto of(LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String phone, LocalDateTime expiredAt, int verificationCode, LocalDateTime verifiedAt) {
        return new AuthDto(createdAt, updatedAt, deleted, phone, expiredAt, verificationCode, verifiedAt);
    }

    public static AuthDto from(Auth entity) {
        return new AuthDto(entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isDeleted(),
                entity.getPhone(),
                entity.getExpiredAt(),
                entity.getVerificationCode(),
                entity.getVerifiedAt());
    }

    public Auth toEntity() {
        return Auth.builder()
                .phone(phone)
                .verificationCode(verificationCode)
                .verifiedAt(verifiedAt)
                .expiredAt(expiredAt)
                .build();
    }


}