package com.msgpick.module.partners.dto;

import com.msgpick.msgpick.code.PartnerRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDetailResponse {
    private Long id;
    private String phone;
    private String email;
    private PartnerRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
}
