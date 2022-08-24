package com.msgpick.module.auth.service;

import com.msgpick.module.auth.repository.AuthRepository;
import com.msgpick.module.partners.domain.Partner;
import com.msgpick.module.partners.repository.PartnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

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
        String phone = "01099999999";
        BDDMockito.given(partnerRepository.save(any(Partner.class)))
                .willReturn(any(Partner.class));

        BDDMockito.then(partnerRepository).should().save(any(Partner.class));


    }

    @Test
    void findVerifications() {
    }
}