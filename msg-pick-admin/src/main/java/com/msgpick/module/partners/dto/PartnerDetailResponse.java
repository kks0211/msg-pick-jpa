package com.msgpick.module.partners.dto;

import com.msgpick.msgpick.code.PartnerRole;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDetailResponse extends BaseEntity {
    private Long partnerId;
    private String phone;
    private String email;
    private PartnerRole partnerRole;
}
