package com.msgpick.module.auth.dto;

import lombok.Getter;

@Getter
public class AuthVerifiedRequest {
    private String phone;

    public PhoneVerification toPhoneVerification() {
        return PhoneVerification.builder().phone(phone).build();
    }
}
