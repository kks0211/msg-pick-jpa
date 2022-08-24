package com.msgpick.module.auth.domain;

import com.msgpick.module.auth.dto.AuthDto;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Getter
@ToString
@Table(name = "phone_verifications")
@NoArgsConstructor
@Entity
public class Auth extends BaseEntity {

    @Id
    private String phone;
    private LocalDateTime expiredAt;
    private int verificationCode;
    private LocalDateTime verifiedAt;

    @Builder
    public Auth(String phone, LocalDateTime expiredAt, int verificationCode, LocalDateTime verifiedAt) {
        this.phone = phone;
        this.expiredAt = expiredAt;
        this.verificationCode = verificationCode;
        this.verifiedAt = verifiedAt;
    }

    public void update(Auth auth) {
        this.phone = auth.getPhone();
        this.verificationCode = auth.getVerificationCode();
        this.expiredAt = auth.getExpiredAt();
    }



}
