package com.msgpick.module.partners.service;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import com.msgpick.module.partners.mapper.PartnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerMapper partnerMapper;

    public PartnerDetailResponse findByPartner(PartnerRegisterRequest request) {
        var partner = partnerMapper.findByPhoneNumber(request.getPhone());
        return partner;
    }

}
