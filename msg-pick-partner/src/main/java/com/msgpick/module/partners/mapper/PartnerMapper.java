package com.msgpick.module.partners.mapper;

import com.msgpick.module.partners.dto.PartnerDetailResponse;

public interface PartnerMapper {
    PartnerDetailResponse findByPhoneNumber(String phone);
    boolean findByEmail(String email);
}
