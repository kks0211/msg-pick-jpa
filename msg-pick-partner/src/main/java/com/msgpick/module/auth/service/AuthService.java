package com.msgpick.module.auth.service;

import com.msgpick.module.auth.domain.Auth;
import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.AuthVerifiedRequest;
import com.msgpick.module.auth.repository.AuthRepository;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import com.msgpick.module.partners.repository.PartnerRepository;
import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final PartnerRepository partnerRepository;


    /*public boolean ifExistEmail(String email) {
        return partnerMapper.findByEmail(email);
    }*/

    @Transactional
    public void registerPartner(PartnerRegisterRequest request) {
        request.encodePassword(passwordEncoder);
        var initPartner = request.toDto();
        partnerRepository.save(initPartner.toEntity());
    }

    @Transactional
    public void registerAuth(AuthVerifiedRequest request) {
        var byPhoneNumber = partnerRepository.findByPhone(request.getPhone());
        if (byPhoneNumber != null) {
            throw new BaseException(ErrorCode.PHONE_ALREADY_USED);
        }

        var phoneVerification = request.toPhoneVerification();
        Auth auth = authRepository.findByPhone(phoneVerification.getPhone()).orElseThrow();

        if (auth == null) {
            authRepository.save(auth);
        } else {
            auth.update(auth);
        }
    }

    @Transactional
    public void findVerifications(AuthCheckVerifiedRequest request) {
        Auth checkVerifiedNumber = authRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new BaseException(ErrorCode.VERIFICATION_NUMBER_NOT_MATCHED));

        authRepository.delete(checkVerifiedNumber);
    }
}
