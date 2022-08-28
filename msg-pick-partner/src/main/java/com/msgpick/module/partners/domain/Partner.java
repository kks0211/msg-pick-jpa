package com.msgpick.module.partners.domain;

import com.msgpick.msgpick.code.PartnerRole;
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
    @Column(name = "partner_id")
    private Long id;

    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private PartnerRole role;

    @Builder
    public Partner(String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = PartnerRole.OWNER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner auth)) return false;
        return id != null && id.equals(auth.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
