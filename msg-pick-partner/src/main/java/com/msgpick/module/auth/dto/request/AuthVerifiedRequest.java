package com.msgpick.module.auth.dto.request;

import com.msgpick.module.auth.dto.PhoneVerificationDto;

public record AuthVerifiedRequest(String phone) {

    public PhoneVerificationDto toPhoneVerification() {
        return new PhoneVerificationDto(phone);
    }
}
