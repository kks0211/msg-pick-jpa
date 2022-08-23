package com.msgpick.module.partners.dto;

import java.time.LocalDateTime;

public record PartnerRegisterRequestDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean deleted,
        Long partnerId,
        String email,
        String phone,
        String password
) {

    /*public static void encodePassword(PasswordEncoder passwordEncoder) {
        this(password);
        password = passwordEncoder.encode(password);
    }*/

    public PartnerDto toDto() {
        return PartnerDto.of(
                email,
                phone,
                password
        );
    }

}
