package com.msgpick.module.partners.domain;

import com.msgpick.msgpick.code.Role;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(name = "partners")
@NoArgsConstructor
@Entity
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Partner(String email, String phone, String password, Role role) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = Role.MANAGER;
    }

    public static Partner of(String email, String phone, String password, Role role) {
        return Partner.builder()
                .email(email)
                .phone(phone)
                .password(password)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner auth)) return false;
        return partnerId != null && partnerId.equals(auth.getPartnerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerId);
    }
}
