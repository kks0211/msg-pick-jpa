package com.msgpick.module.auth.dto;

import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import com.msgpick.msgpick.global.util.VerificationCodeGenerator;

import java.time.LocalDateTime;

public record PhoneVerificationDto(LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String phone,
                                   LocalDateTime expiredAt, int verificationCode, LocalDateTime verifiedAt) {

    private static final long EXPIRED_MINUTES = 2;

    public PhoneVerificationDto(String phone, LocalDateTime expiredAt, int verificationCode) {
        this(null, null, false, phone, expiredAt, verificationCode, null);
    }

    public PhoneVerificationDto(String phone) {
        this(null, null, false, phone, LocalDateTime.now().plusMinutes(EXPIRED_MINUTES), VerificationCodeGenerator.randomNumber(), null);
    }

    public void checkExpired() {
        if (LocalDateTime.now().isAfter(expiredAt)) {
            throw new BaseException(ErrorCode.VERIFICATION_NUMBER_NOT_MATCHED);
        }
    }

    /**
    public void updateVerificationInfo(int verificationCode) {
        this.verificationCode = verificationCode;
        this.expiredAt = LocalDateTime.now().plusMinutes(EXPIRED_MINUTES);
    }

    public void verified() {
        this.verifiedAt = LocalDateTime.now();
    }
     */
}
