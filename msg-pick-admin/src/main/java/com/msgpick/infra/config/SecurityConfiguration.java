package com.msgpick.infra.config;

import com.msgpick.infra.security.CustomAccessDeniedHandler;
import com.msgpick.infra.security.CustomAuthenticationFailureHandler;
import com.msgpick.infra.security.CustomAuthenticationProvider;
import com.msgpick.infra.security.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public CustomAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/assets/main*", "/images/**", "/themes/**", "/error");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/auth/**", "/shops/**", "/partners","/partners/**" , "/shops", "/admins/**", "/programs/**", "/api/v1/shops/**", "/api/v1/programs/**", "/api/v1/therapists/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/auth/sign-in")
                .loginProcessingUrl("/auth/sign-in")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .usernameParameter("loginId")
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

    }

}
