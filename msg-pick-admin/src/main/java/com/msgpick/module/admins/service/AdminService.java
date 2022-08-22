package com.msgpick.module.admins.service;

import com.msgpick.module.admins.dto.AdminDetailResponse;
import com.msgpick.module.admins.dto.AdminRegisterRequest;
import com.msgpick.module.admins.mapper.AdminMapper;
import com.msgpick.msgpick.global.domain.PaginationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public Long registerAdmin(AdminRegisterRequest request) {
        request.encodePassword(passwordEncoder);
        var result = adminMapper.save(request);
        return result;
    }

    public AdminDetailResponse findAdmin(AdminRegisterRequest request) {
        return adminMapper.findByLoginId(request.getName());
    }

    public List<AdminDetailResponse> findAdminList(AdminDetailResponse response) {

        var totalCount = adminMapper.getTotalCount();

        PaginationInfo paginationInfo = new PaginationInfo(response);
        paginationInfo.setTotalRecordCount(totalCount);

        response.setPaginationInfo(paginationInfo);

        return adminMapper.findAdminList(response);
    }
}
