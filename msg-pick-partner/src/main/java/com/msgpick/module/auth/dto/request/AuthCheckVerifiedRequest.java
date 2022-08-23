package com.msgpick.module.auth.dto.request;

import com.msgpick.module.auth.dto.PhoneVerificationDto;

public record AuthCheckVerifiedRequest(String phone, int verificationCode) {

    public AuthCheckVerifiedRequest (PhoneVerificationDto phoneVerification) {
        this(phoneVerification.phone(), phoneVerification.verificationCode());

    }
}
