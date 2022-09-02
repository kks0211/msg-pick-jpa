package com.msgpick.module.auth.service;

import com.msgpick.module.auth.domain.Auth;
import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.PhoneVerification;
import com.msgpick.module.auth.repository.AuthRepository;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import com.msgpick.module.partners.repository.PartnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("핸드폰 인증")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private AuthRepository authRepository;
    @Mock
    private PartnerRepository partnerRepository;

    @Test
    void registerPartner() {
    }

    @Test
    void registerAuth() {


//        PartnerRegisterRequest partnerRegisterRequest =
//                new PartnerRegisterRequest("0109999999", "test@test.com", passwordEncoder.encode("123"));
//
//        var initDto = partnerRegisterRequest.toDto();
//
//        var initEntity = initDto.toEntity();
//
//
//        given(partnerRepository.save(any())).willReturn(initEntity);
//
//        authService.registerPartner(partnerRegisterRequest);
//
//
//        verify(authService).registerPartner(partnerRegisterRequest);



    }

    @Test
    void findVerifications() {
        String phone = "010245090387";

        PhoneVerification p = new PhoneVerification(phone);
        AuthCheckVerifiedRequest a = new AuthCheckVerifiedRequest(p);

        given(authRepository.findByPhone(phone))
                .willReturn(Optional.of(new Auth(phone, LocalDateTime.now(), a.getVerificationCode(), LocalDateTime.now())));

        authService.findVerifications(a);

    }
}