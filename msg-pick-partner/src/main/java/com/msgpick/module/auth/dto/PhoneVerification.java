package com.msgpick.module.auth.dto;

import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import com.msgpick.msgpick.global.entity.BaseEntity;
import com.msgpick.msgpick.global.util.VerificationCodeGenerator;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Getter
public class PhoneVerification extends BaseEntity {

    private Long phoneVerificationId;
    private String phone;
    private int verificationCode;
    private LocalDateTime verifiedAt;
    private LocalDateTime expiredAt;
    private final long EXPIRED_MINUTES = 2;

    @Builder
    public PhoneVerification(String phone) {
        if (StringUtils.isEmpty(phone)) throw new BaseException(ErrorCode.PHONE_NOT_FOUND);

        this.phone = phone;
        this.verificationCode = VerificationCodeGenerator.randomNumber();
        this.expiredAt = LocalDateTime.now().plusMinutes(EXPIRED_MINUTES);
    }

    public void checkExpired() {
        if (LocalDateTime.now().isAfter(expiredAt)) {
            throw new BaseException(ErrorCode.VERIFICATION_NUMBER_NOT_MATCHED);
        }
    }

    public void updateVerificationInfo(int verificationCode) {
        this.verificationCode = verificationCode;
        this.expiredAt = LocalDateTime.now().plusMinutes(EXPIRED_MINUTES);
    }

    public void verified() {
        this.verifiedAt = LocalDateTime.now();
    }

}
