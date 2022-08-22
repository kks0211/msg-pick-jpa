package com.msgpick.module.programs.service;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.mapper.ProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramMapper programMapper;

    @Transactional(rollbackFor = Exception.class)
    public void registerProgramApi(Object programSession, Long shopId) {
        List<ProgramRegisterRequest> request = (List<ProgramRegisterRequest>) programSession;

        var programList = request.stream().map(program -> {
            program.setShopId(shopId);
            return program;
        }).collect(Collectors.toList());

        programMapper.save(programList);
    }

    @Transactional(readOnly = true)
    public List<ProgramDetailResponse> findProgramList(Long shopId) {
        return programMapper.findByProgramList(shopId);
    }

}
