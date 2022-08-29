package com.msgpick.module.programs.service;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramUpdateRequest;
import com.msgpick.module.programs.mapper.ProgramMapper;
import com.msgpick.module.programs.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Transactional(readOnly = true)
    public List<ProgramDetailResponse> findProgramList(Long shopId) {
        return programMapper.findByProgramList(shopId);
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
        return programMapper.findByProgram(programId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeProgram(Long programId) {
        programMapper.delete(programId);
    }
}
