package com.msgpick.module.partners.dto;

import com.msgpick.msgpick.code.PartnerRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class PartnerRegisterRequest {
    private String phone;
    private String email;
    private String password;
    private PartnerRole partnerRole;

    @Builder
    public PartnerRegisterRequest(String phone, String email, String password) {
        if(StringUtils.isEmpty(phone)) throw new RuntimeException("휴대폰 번호를 입력해주세요.");
        if(StringUtils.isEmpty(password)) throw new RuntimeException("비밀번호를 입력해주세요.");
        if(StringUtils.isEmpty(email)) throw new RuntimeException("이메일을 입력해주세요.");

        this.phone = phone;
        this.email = email;
        this.password = password;
        this.partnerRole = PartnerRole.OWNER;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
