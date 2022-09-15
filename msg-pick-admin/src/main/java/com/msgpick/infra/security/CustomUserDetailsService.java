package com.msgpick.infra.security;

import com.msgpick.module.admins.dto.AdminDto;
import com.msgpick.module.admins.repository.AdminRepository;
import com.msgpick.msgpick.global.common.exception.InvalidParamException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        AdminDto.DetailResponse admin = adminRepository.findByLoginId(loginId);

        if (admin == null) {
            throw new InvalidParamException(ErrorCode.USERNAME_PASSWORD_INVALID);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(admin.getRole().name()));

        return new CustomUserDetails(admin);

    }

}
