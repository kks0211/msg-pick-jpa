package com.msgpick.infra.security;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.module.partners.mapper.PartnerMapper;
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

    private final PartnerMapper partnerMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        PartnerDetailResponse partner = partnerMapper.findByPhoneNumber(phone);

        if (partner == null) {
            throw new InvalidParamException(ErrorCode.PHONE_PASSWORD_CONFIRM_NOT_MATCHED);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("OWNER"));
        //roles.add(new SimpleGrantedAuthority(partner.getRole()));

        return new CustomUserDetails(partner);

    }

}
