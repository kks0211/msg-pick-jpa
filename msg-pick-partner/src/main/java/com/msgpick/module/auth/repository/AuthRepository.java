package com.msgpick.module.auth.repository;

import com.msgpick.module.auth.domain.Auth;
import com.msgpick.module.auth.dto.AuthDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findByPhone(String phone);
}
