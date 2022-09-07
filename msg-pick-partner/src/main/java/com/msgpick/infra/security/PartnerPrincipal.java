package com.msgpick.infra.security;

import com.msgpick.module.partners.dto.PartnerDto;
import com.msgpick.msgpick.code.PartnerRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record PartnerPrincipal(
        Long id,
        String phone,
        String email,
        String password,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static PartnerPrincipal of(Long id, String phone, String email, String password) {
        Set<PartnerRole> roleTypes = Set.of(PartnerRole.OWNER);

        return new PartnerPrincipal(
                id,
                phone,
                email,
                password,
                roleTypes.stream()
                        .map(PartnerRole::getDescription)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static PartnerPrincipal from(PartnerDto dto) {
        return PartnerPrincipal.of(
                dto.id(),
                dto.phone(),
                dto.email(),
                dto.password()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
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
