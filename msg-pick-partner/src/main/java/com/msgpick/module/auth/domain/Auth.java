package com.msgpick.module.auth.domain;

import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Integer verificationCode;
    private LocalDateTime verifiedAt;

}
