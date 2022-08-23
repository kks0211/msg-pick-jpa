package com.msgpick.module.therapists.mapper;

import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.dto.TherapistUpdateRequest;

import java.util.List;

public interface TherapistMapper {
    int saveAll(List<TherapistRegisterRequest> therapist);

    List<TherapistDetailResponse> findByTherapistListPage(TherapistDetailResponse therapistDetailResponse);

    List<TherapistDetailResponse> findByTherapistList(Long shopId);

    int getTotalCount();

    int save(TherapistUpdateRequest therapist);

    int update(TherapistUpdateRequest therapist);

    TherapistDetailResponse findByTherapist(Long therapistId);

    void delete(Long therapistId);
}
