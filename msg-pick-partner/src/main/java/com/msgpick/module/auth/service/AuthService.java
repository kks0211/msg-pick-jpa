package com.msgpick.module.auth.service;

import com.msgpick.module.auth.domain.Auth;
import com.msgpick.module.auth.dto.request.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.request.AuthVerifiedRequest;
import com.msgpick.module.auth.repository.AuthRepository;
import com.msgpick.module.partners.dto.PartnerRegisterRequestDto;
import com.msgpick.module.partners.mapper.PartnerMapper;
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
    private final PartnerMapper partnerMapper;


    public boolean ifExistEmail(String email) {
        return partnerMapper.findByEmail(email);
    }

    @Transactional
    public void registerPartner(PartnerRegisterRequestDto request) {
        //request.encodePassword(passwordEncoder);
        var partnerDto = request.toDto();
        partnerRepository.save(partnerDto.toEntity());
    }

    @Transactional
    public void registerAuth(AuthVerifiedRequest request) {
        var byPhoneNumber = partnerRepository.findByPhone(request.phone());
        if (byPhoneNumber != null) {
            throw new BaseException(ErrorCode.PHONE_ALREADY_USED);
        }

        var phoneVerification =  request.toPhoneVerification();
        Auth auth =  authRepository.findByPhone(phoneVerification.phone()).orElseThrow();

        if(auth == null) {
            authRepository.save(auth).isDeleted();
        } else {
            auth.update(auth);
        }
    }

    @Transactional
    public void findVerifications(AuthCheckVerifiedRequest request) {
        Auth checkVerifiedNumber = authRepository.findByPhone(request.phone())
                                        .orElseThrow(() -> new BaseException(ErrorCode.VERIFICATION_NUMBER_NOT_MATCHED));

        authRepository.delete(checkVerifiedNumber);
    }
}
