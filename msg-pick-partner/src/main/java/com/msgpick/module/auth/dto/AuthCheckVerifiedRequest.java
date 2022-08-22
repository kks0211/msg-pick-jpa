package com.msgpick.module.auth.dto;

import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCheckVerifiedRequest {
    private String phone;
    private int verificationCode;

    public AuthCheckVerifiedRequest(PhoneVerification phoneVerification) {
        if (StringUtils.isEmpty(phoneVerification.getPhone())) throw new BaseException(ErrorCode.PHONE_NOT_FOUND);
        if (phoneVerification.getVerificationCode() == 0) throw new BaseException(ErrorCode.VERIFICATION_NOT_FOUND);

        this.phone = phoneVerification.getPhone();
        this.verificationCode = phoneVerification.getVerificationCode();
    }
}
