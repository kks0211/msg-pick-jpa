package com.msgpick.module.therapists.service;

import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.mapper.TherapistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TherapistService {

    private final TherapistMapper therapistMapper;

    @Transactional(rollbackFor = Exception.class)
    public int registerTherapist(List<TherapistRegisterRequest> registerTherapistList, Long shopId) {
        var therapistList = registerTherapistList.stream().map(therapist -> {
            therapist.setShopId(shopId);
            return therapist;
        }).collect(Collectors.toList());

        return therapistMapper.save(therapistList);
    }

    @Transactional(readOnly = true)
    public List<TherapistDetailResponse> findTherapistList(Long shopId) {
        return therapistMapper.findByTherapistList(shopId);
    }

}
