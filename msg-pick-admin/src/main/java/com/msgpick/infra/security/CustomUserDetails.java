package com.msgpick.infra.security;

import com.msgpick.module.admins.dto.AdminDetailResponse;
import com.msgpick.msgpick.code.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private Long partnerId;
    private String loginId;
    private String name;
    private String password;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(AdminDetailResponse admin) {
        this.partnerId = admin.getAdminId();
        this.loginId = admin.getLoginId();
        this.name = admin.getName();
        this.password = admin.getPassword();
        this.role = admin.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
