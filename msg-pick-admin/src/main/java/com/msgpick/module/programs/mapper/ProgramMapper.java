package com.msgpick.module.programs.mapper;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    int save(List<ProgramRegisterRequest> program);
    List<ProgramDetailResponse> findByProgramList(Long shopId);
}
