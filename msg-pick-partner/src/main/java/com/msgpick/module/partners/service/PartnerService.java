package com.msgpick.module.partners.service;

import com.msgpick.module.partners.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

//    @Transactional(readOnly = true)
//    public PartnerDetailResponse findByPartner(PartnerRegisterRequest request) {
//        return partnerRepository.findById(request.getPhone())
//                .map(PartnerDetailResponse::from)
//                .orElseThrow(() -> new BaseException(ErrorCode.COMMON_ENTITY_NOT_FOUND));
//    }

}


