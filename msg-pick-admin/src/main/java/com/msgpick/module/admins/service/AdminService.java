package com.msgpick.module.admins.service;

import com.msgpick.module.admins.dto.AdminDto;
import com.msgpick.module.admins.repository.AdminRepository;
import com.msgpick.msgpick.global.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public Long registerAdmin(AdminDto.RegisterRequest request) {
        var initEntity = request.toEntity();
        initEntity.encodePassword(passwordEncoder);
        return adminRepository.save(initEntity).getId();
    }

    public AdminDto.DetailResponse findAdmin(Long adminId) {
        return adminRepository.findById(adminId).map(AdminDto.DetailResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("해당 관리자가 없습니다 : " + adminId));
    }

    public List<AdminDto.DetailResponse> findAdminList() {

        return adminRepository.findAll().stream()
                .map(AdminDto.DetailResponse::new)
                .collect(Collectors.toList());
    }
}
