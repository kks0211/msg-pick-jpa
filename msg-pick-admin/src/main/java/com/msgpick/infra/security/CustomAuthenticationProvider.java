package com.msgpick.infra.security;

import com.msgpick.msgpick.global.common.response.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String adminAccount = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(adminAccount);

        if (!passwordEncoder.matches(password, customUserDetails.getPassword())) {
            throw new BadCredentialsException(ErrorCode.EMAIL_PASSWORD_INVALID.getErrorMsg());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, password, null);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
