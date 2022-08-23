package com.msgpick.module.partners.service;

import com.msgpick.module.partners.dto.PartnerDetailResponseDto;
import com.msgpick.module.partners.dto.PartnerRegisterRequestDto;
import com.msgpick.module.partners.repository.PartnerRepository;
import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    @Transactional(readOnly = true)
    public PartnerDetailResponseDto findByPartner(PartnerRegisterRequestDto request) {
        return partnerRepository.findById(request.partnerId())
                .map(PartnerDetailResponseDto::from)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMON_ENTITY_NOT_FOUND));
    }

}


