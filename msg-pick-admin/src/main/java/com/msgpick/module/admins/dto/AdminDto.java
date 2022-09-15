package com.msgpick.module.admins.dto;

import com.msgpick.module.admins.domain.Admin;
import com.msgpick.msgpick.code.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class AdminDto {

    @Builder
    @Getter
    public static class RegisterRequest {
        private String loginId;
        private String name;
        private String password;

        public Admin toEntity() {
            return Admin.builder()
                    .loginId(loginId)
                    .name(name)
                    .password(password)
                    .build();
        }
    }

    @Getter
    public static class DetailResponse {
        private Long id;
        private String loginId;
        private String name;
        private String password;
        private Role role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean deleted;

        public DetailResponse(Admin entity) {
            this.id = entity.getId();
            this.loginId = entity.getLoginId();
            this.name = entity.getName();
            this.password = entity.getPassword();
            this.role = entity.getRole();
            this.createdAt = entity.getCreatedAt();
            this.updatedAt = entity.getUpdatedAt();
            this.deleted = entity.getDeleted();
        }
    }
}
