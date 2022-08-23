package com.msgpick.infra.config;

import com.msgpick.infra.security.PartnerPrincipal;
import com.msgpick.module.partners.dto.PartnerDto;
import com.msgpick.module.partners.repository.PartnerRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/",
                                "/auth/sign-up",
                                "/api/v1/auth/**",
                                "/shops/**",
                                "/shops",
                                "/programs/**",
                                "/api/v1/shops/**",
                                "/api/v1/programs/**",
                                "/api/v1/therapists/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/auth/sign-in")
                .loginProcessingUrl("/auth/sign-in")
                .usernameParameter("phone")
                .and()
                .logout()
                .logoutUrl("/Logout")
                .invalidateHttpSession(true)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/?expire=true")
                .and().and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(PartnerRepository partner) {
        return phone -> partner
                .findByPhone(phone)
                .map(PartnerDto::from)
                .map(PartnerPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - phone: " + phone));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
