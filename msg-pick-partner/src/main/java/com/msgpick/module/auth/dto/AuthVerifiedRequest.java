package com.msgpick.module.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthVerifiedRequest {
    private String phone;

    public PhoneVerification toPhoneVerification() {
        return PhoneVerification.builder().phone(phone).build();
    }
}
