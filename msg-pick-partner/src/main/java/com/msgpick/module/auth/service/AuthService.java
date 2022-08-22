package com.msgpick.module.auth.service;

import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.AuthVerifiedRequest;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import com.msgpick.module.auth.mapper.AuthMapper;
import com.msgpick.module.partners.mapper.PartnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final PartnerMapper partnerMapper;


    public boolean ifExistEmail(String email) {
        return partnerMapper.findByEmail(email);
    }

    @Transactional
    public Long registerPartner(PartnerRegisterRequest request) {
        request.encodePassword(passwordEncoder);
        return partnerMapper.save(request);
    }

    @Transactional
    public int registerAuth(AuthVerifiedRequest request) {
        var byPhoneNumber = partnerMapper.findByPhoneNumber(request.getPhone());
        if (byPhoneNumber != null) {
            throw new BaseException(ErrorCode.PHONE_ALREADY_USED);
        }

        var phoneVerification =  request.toPhoneVerification();
        var auth = authMapper.findByPhoneVerifications(request.getPhone());
        if(auth == null) {
            return authMapper.save(phoneVerification);
        }
        return authMapper.update(phoneVerification);
    }

    public boolean findVerifications(AuthCheckVerifiedRequest request) {
        var checkVerifiedNumber = authMapper.findByVerificationCode(request);
        if(!checkVerifiedNumber) {
            throw new BaseException(ErrorCode.VERIFICATION_NUMBER_NOT_MATCHED);
        }
        var authComplete = authMapper.delete(request.getPhone());
        return checkVerifiedNumber;
    }
}
