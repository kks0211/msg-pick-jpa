package com.msgpick.module.partners.mapper;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;

public interface PartnerMapper {
    Long save(PartnerRegisterRequest partner);
    PartnerDetailResponse findByPhoneNumber(String phone);
    boolean findByEmail(String email);
}
