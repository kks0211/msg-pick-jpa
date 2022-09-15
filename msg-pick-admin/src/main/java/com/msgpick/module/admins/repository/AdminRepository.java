package com.msgpick.module.admins.repository;

import com.msgpick.module.admins.domain.Admin;
import com.msgpick.module.admins.dto.AdminDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    AdminDto.DetailResponse findByLoginId(String loginId);
}
