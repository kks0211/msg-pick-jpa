package com.msgpick.module.programs.mapper;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.dto.ProgramUpdateRequest;

import java.util.List;

public interface ProgramMapper {
    int saveAll(List<ProgramRegisterRequest> program);
    int save(ProgramUpdateRequest program);
    List<ProgramDetailResponse> findByProgramList(Long shopId);
    int update(ProgramUpdateRequest program);
    void delete(Long programId);
    ProgramDetailResponse findByProgram(Long programId);
}
