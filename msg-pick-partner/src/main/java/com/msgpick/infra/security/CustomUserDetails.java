package com.msgpick.infra.security;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.msgpick.code.PartnerRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private Long partnerId;
    private String phone;
    private String email;
    private String password;
    private PartnerRole partnerRole;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(PartnerDetailResponse partner) {
        this.partnerId = partner.getPartnerId();
        this.email = partner.getEmail();
        this.password = partner.getPassword();
        this.phone = partner.getPhone();
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
        return email;
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
