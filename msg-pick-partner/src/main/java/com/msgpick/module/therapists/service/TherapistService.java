package com.msgpick.module.therapists.service;

import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistUpdateRequest;
import com.msgpick.module.therapists.mapper.TherapistMapper;
import com.msgpick.msgpick.global.domain.PaginationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapistService {

    private final TherapistMapper therapistMapper;

    @Transactional(readOnly = true)
    public List<TherapistDetailResponse> findTherapistListPage(TherapistDetailResponse therapistDetailResponse) {

        var totalCount = therapistMapper.getTotalCount();

       /* PaginationInfo paginationInfo = new PaginationInfo(therapistDetailResponse);
        paginationInfo.setTotalRecordCount(totalCount);

        therapistDetailResponse.setPaginationInfo(paginationInfo);

        return therapistMapper.findByTherapistListPage(therapistDetailResponse);*/
        return null;
    }

    @Transactional(readOnly = true)
    public List<TherapistDetailResponse> findTherapistList(Long shopId) {
        return therapistMapper.findByTherapistList(shopId);
    }

    @Transactional(rollbackFor = Exception.class)
    public int modifyTherapist(Long therapistId, TherapistUpdateRequest therapist) {

        if (therapistId == null) {
            return therapistMapper.save(therapist);
        }

        therapist.setTherapistId(therapistId);
        return therapistMapper.update(therapist);
    }

    @Transactional(readOnly = true)
    public TherapistDetailResponse findTherapist(Long therapistId) {
        return therapistMapper.findByTherapist(therapistId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeTherapist(Long therapistId) {
        therapistMapper.delete(therapistId);
    }
}
