package com.msgpick.module.partners.service;

import com.msgpick.module.partners.dto.PartnerDetailResponse;
import com.msgpick.module.partners.mapper.PartnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerMapper partnerMapper;

    public PartnerDetailResponse findByPartner(Long partnerId) {
        return partnerMapper.findByPartnerId(partnerId);
    }

    public List<PartnerDetailResponse> findByPartnerList() {
        return partnerMapper.findByPartnerList();
    }

}
