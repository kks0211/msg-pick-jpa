package com.msgpick.module.programs.service;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramUpdateRequest;
import com.msgpick.module.programs.mapper.ProgramMapper;
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
    private final ProgramMapper programMapper;

    @Transactional(readOnly = true)
    public List<ProgramDetailResponse> findProgramList(Long shopId) {
        return programRepository.findByShop_Id(shopId).stream()
                .map(ProgramDetailResponse::toDto)
                .collect(Collectors.toList());
        //return programMapper.findByProgramList(shopId);
    }

    @Transactional(rollbackFor = Exception.class)
    public int modifyProgram(Long programId, ProgramUpdateRequest program) {

        if (programId == null) {
            return programMapper.save(program);
        }
        program.setProgramId(programId);
        return programMapper.update(program);
    }

    @Transactional(readOnly = true)
    public ProgramDetailResponse findProgram(Long programId) {
        return programRepository.findById(programId)
                .map(ProgramDetailResponse::toDto)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 없습니다 : " + programId));

//        return programMapper.findByProgram(programId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeProgram(Long programId) {
        var entity = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 없습니다 : " + programId));
        programRepository.delete(entity);
        //programMapper.delete(programId);
    }
}
