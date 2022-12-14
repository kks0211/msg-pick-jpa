package com.msgpick.module.programs.service;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramUpdateRequest;
import com.msgpick.module.programs.repository.ProgramRepository;
import com.msgpick.msgpick.global.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    @Transactional(readOnly = true)
    public List<ProgramDetailResponse> findProgramList(Long shopId) {
        return programRepository.findByShop_Id(shopId).stream()
                .map(ProgramDetailResponse::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyProgram(Long programId, ProgramUpdateRequest request) {

        var findProgram = programRepository.getReferenceById(programId);

        if (findProgram != null) {
            findProgram.update(request);
        } else {
            programRepository.save(request.toEntity());
        }

    }

    @Transactional(readOnly = true)
    public ProgramDetailResponse findProgram(Long programId) {
        return programRepository.findById(programId)
                .map(ProgramDetailResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 없습니다 : " + programId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeProgram(Long programId) {
        var entity = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 없습니다 : " + programId));
        programRepository.delete(entity);
    }

}
