package com.msgpick.module.programs.controller;

import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.dto.ProgramUpdateRequest;
import com.msgpick.module.programs.service.ProgramService;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class ProgramApiController {

    private final ProgramService programService;

    @PostMapping("/register")
    public CommonResponse registerProgramApi(@RequestBody @Valid List<ProgramRegisterRequest> programList) throws Exception {
        SessionUtil.setAttribute(SessionUtil.REGISTER_PROGRAM_INFO, programList);
        return CommonResponse.success(null);
    }

    @PutMapping(value = {"/{programId}", "/"})
    public CommonResponse modifyProgramApi(@PathVariable(required = false) Long programId, @RequestBody @Valid ProgramUpdateRequest program) {

        programService.modifyProgram(programId, program);

        var updateProgramInfo = programService.findProgram(program.getProgramId());

        return CommonResponse.success(updateProgramInfo);

    }

    @PatchMapping(value = {"/{programId}", "/"})
    public CommonResponse removeProgramApi(@PathVariable(required = false) Long programId) {

        programService.removeProgram(programId);

        return CommonResponse.success(null);
    }

}

