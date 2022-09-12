package com.msgpick.module.therapists.service;

import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistUpdateRequest;
import com.msgpick.module.therapists.repository.TherapistRepository;
import com.msgpick.msgpick.global.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TherapistService {

    private final TherapistRepository therapistRepository;

    @Transactional(readOnly = true)
    public List<TherapistDetailResponse> findTherapistListPage(TherapistDetailResponse therapistDetailResponse) {

        // var totalCount = therapistMapper.getTotalCount();
        var totalCount = therapistRepository.count();

       /* PaginationInfo paginationInfo = new PaginationInfo(therapistDetailResponse);
        paginationInfo.setTotalRecordCount(totalCount);

        therapistDetailResponse.setPaginationInfo(paginationInfo);

        return therapistMapper.findByTherapistListPage(therapistDetailResponse);*/
        return null;
    }

    @Transactional(readOnly = true)
    public List<TherapistDetailResponse> findTherapistList(Long shopId) {
        return therapistRepository.findByShop_Id(shopId).stream()
                .map(TherapistDetailResponse::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyTherapist(Long therapistId, TherapistUpdateRequest request) {

        var findTherapist = therapistRepository.getReferenceById(therapistId);

        if (findTherapist != null) {
            findTherapist.update(request);
        } else {
            therapistRepository.save(request.toEntity());
        }

    }

    @Transactional(readOnly = true)
    public TherapistDetailResponse findTherapist(Long therapistId) {

        return therapistRepository.findById(therapistId)
                .map(TherapistDetailResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("해당 마시자사가 없습니다 : " + therapistId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeTherapist(Long therapistId) {
        var entity = therapistRepository.findById(therapistId)
                .orElseThrow(() -> new EntityNotFoundException("해당 마시자사가 없습니다 : " + therapistId));

        therapistRepository.delete(entity);
    }
}
