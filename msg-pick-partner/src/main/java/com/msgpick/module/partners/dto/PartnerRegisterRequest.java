package com.msgpick.module.partners.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Getter
public class PartnerRegisterRequest {
    private String phone;
    private String email;
    @Setter
    private String password;

    public PartnerDto toDto() {
        return PartnerDto.of(phone, email, password);
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

}
