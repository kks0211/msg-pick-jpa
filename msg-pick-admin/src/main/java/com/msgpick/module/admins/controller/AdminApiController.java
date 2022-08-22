package com.msgpick.module.admins.controller;

import com.msgpick.module.admins.dto.AdminRegisterRequest;
import com.msgpick.module.admins.service.AdminService;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/register")
    public CommonResponse registerAdmin(@RequestBody @Valid AdminRegisterRequest request) {

        var initAdmin = adminService.registerAdmin(request);

        return CommonResponse.success(null, "관리자 등록 성공");
    }

}
