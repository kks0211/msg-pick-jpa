package com.msgpick.module.admins.dto;

import com.msgpick.msgpick.code.Role;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class AdminRegisterRequest {
    private String loginId;
    private String name;
    private String password;
    private Role role;

    @Builder
    public AdminRegisterRequest(String loginId, String name, String password, Role role) {
        if (StringUtils.isEmpty(loginId)) throw new RuntimeException("아이디를 입력해주세요.");
        if (StringUtils.isEmpty(password)) throw new RuntimeException("비밀번호를 입력해주세요.");

        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
