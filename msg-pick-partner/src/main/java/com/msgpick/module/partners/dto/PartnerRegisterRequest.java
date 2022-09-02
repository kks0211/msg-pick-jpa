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

    public PartnerRegisterRequest(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public PartnerDto toDto() {
        return PartnerDto.of(phone, email, password);
    }

}
