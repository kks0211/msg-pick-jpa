package com.msgpick.module.admins.domain;

import com.msgpick.msgpick.code.Role;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "admins")
@Entity
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String LoginId;
    private String name;
    private String password;
    private Role role;

    @Builder
    public Admin(String loginId, String name, String password) {
        this.LoginId = loginId;
        this.name = name;
        this.password = password;
        this.role = Role.MANAGER;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
