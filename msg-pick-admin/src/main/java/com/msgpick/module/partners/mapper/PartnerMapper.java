package com.msgpick.module.partners.mapper;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartnerMapper {
    Long save(PartnerRegisterRequest partner);

    PartnerDetailResponse findByPartnerId(Long partnerId);

    List<PartnerDetailResponse> findByPartnerList();
}
