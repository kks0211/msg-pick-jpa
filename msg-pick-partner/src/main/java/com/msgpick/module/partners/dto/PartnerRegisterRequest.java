package com.msgpick.module.partners.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

}
