package com.msgpick.module.partners.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class PartnerRegisterRequest {
    @NotEmpty(message = "핸드픈 번호는 필수 값 입니다.")
    private String phone;
    @Email(message = "이메일 형식이 아닙니다.")
    @NotEmpty(message = "이메일은 필수 값 입니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 값 입니다.")
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
