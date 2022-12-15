package com.msgpick.module.auth.service;

import com.msgpick.module.auth.domain.Auth;
import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.AuthVerifiedRequest;
import com.msgpick.module.auth.dto.PhoneVerification;
import com.msgpick.module.auth.repository.AuthRepository;
import com.msgpick.module.partners.domain.Partner;
import com.msgpick.module.partners.dto.PartnerDto;
import com.msgpick.module.partners.repository.PartnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("핸드폰 인증")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private AuthRepository authRepository;
    @Mock
    private PartnerRepository partnerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void registerPartner() {
        PartnerDto initDto = PartnerDto.of("01000000000", "test@test.com", "pw123");

        var initPartner = initDto.toEntity(passwordEncoder);
        given(partnerRepository.save(any(Partner.class))).willReturn(initPartner);

        authService.registerPartner(initDto);

        then(partnerRepository).should().save(any(Partner.class));
    }

    @Test
    void registerAuth() {
        AuthVerifiedRequest request = new AuthVerifiedRequest("01000000000");

        given(partnerRepository.findByPhone("01000000000")).willReturn(Optional.ofNullable(null));
        given(authRepository.findByPhone("01000000000")).willReturn(Optional.ofNullable(any(Auth.class)));

        authService.registerAuth(request);

        then(partnerRepository).should().findByPhone("01000000000");
        then(authRepository).should().findByPhone("01000000000");

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