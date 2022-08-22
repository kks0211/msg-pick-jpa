package com.msgpick.module.admins.dto;

import com.msgpick.msgpick.code.Role;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDetailResponse extends BaseEntity {
    private Long adminId;
    private String loginId;
    private String name;
    private String password;
    private Role role;
}
