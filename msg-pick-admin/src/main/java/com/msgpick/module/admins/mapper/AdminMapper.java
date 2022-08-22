package com.msgpick.module.admins.mapper;

import com.msgpick.module.admins.dto.AdminDetailResponse;
import com.msgpick.module.admins.dto.AdminRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Long save(AdminRegisterRequest admin);
    AdminDetailResponse findByLoginId(String name);

    List<AdminDetailResponse> findAdminList(AdminDetailResponse response);
    int getTotalCount();
}
