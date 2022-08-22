package com.msgpick.module.programs.controller;

import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import com.msgpick.msgpick.utils.SessionUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/programs")
public class ProgramApiController {

    @PostMapping("/register")
    public CommonResponse registerProgramApi(@RequestBody @Valid List<ProgramRegisterRequest> programList) throws Exception {
        SessionUtil.setAttribute(SessionUtil.REGISTER_PROGRAM_INFO, programList);
        return CommonResponse.success(null);
    }

}
