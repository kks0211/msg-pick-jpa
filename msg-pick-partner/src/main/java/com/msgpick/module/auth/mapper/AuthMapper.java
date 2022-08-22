package com.msgpick.module.auth.mapper;

import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.PhoneVerification;


public interface AuthMapper {
    int save(PhoneVerification phoneVerification);
    int update(PhoneVerification phoneVerification);
    int delete(String phone);
    boolean findByVerificationCode(AuthCheckVerifiedRequest request);
    AuthCheckVerifiedRequest findByPhoneVerifications(String phone);
}
