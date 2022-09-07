package com.msgpick.infra.config;

import com.msgpick.infra.security.CustomAccessDeniedHandler;
import com.msgpick.infra.security.CustomAuthenticationFailureHandler;
import com.msgpick.infra.security.CustomAuthenticationSuccessHandler;
import com.msgpick.infra.security.PartnerPrincipal;
import com.msgpick.module.partners.dto.PartnerDto;
import com.msgpick.module.partners.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/",
                                "/auth/sign-up",
                                "/auth/sign-in",
                                "/api/v1/auth/**",
                                "/shops/**",
                                "/shops",
                                "/programs/**",
                                "/api/v1/shops/**",
                                "/api/v1/programs/**",
                                "/api/v1/therapists/**",
                                "/favicon.ico", "/css/**", "/assets/main*", "/images/**", "/themes/**", "/error"
                        ).permitAll()
                        .mvcMatchers(HttpMethod.POST, "/api/v1/auth/phone-verifications/new").permitAll()

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
                .build();*/

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/favicon.ico", "/css/**", "/assets/main*", "/images/**", "/themes/**", "/error").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/auth/sign-up").permitAll()
                .antMatchers("/api/v1/auth/**", "/shops/**", "/shops", "/programs/**", "/api/v1/shops/**", "/api/v1/programs/**", "/api/v1/therapists/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/auth/sign-in")
                .loginProcessingUrl("/auth/sign-in")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .usernameParameter("phone")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

        http.logout()
                .logoutUrl("/Logout")
                .invalidateHttpSession(true);

        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/?expire=true");

        return http.build();
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
