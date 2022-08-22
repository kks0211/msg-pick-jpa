package com.msgpick.module.auth.repository;

import com.msgpick.module.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}
