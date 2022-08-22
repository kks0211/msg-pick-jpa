package com.msgpick.module.therapists.mapper;

import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TherapistMapper {
    int save(List<TherapistRegisterRequest> therapist);

    List<TherapistDetailResponse> findByTherapistList(Long shopId);

}
