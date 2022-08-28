package com.msgpick.module.auth.controller;

import com.msgpick.module.auth.dto.AuthCheckVerifiedRequest;
import com.msgpick.module.auth.dto.AuthVerifiedRequest;
import com.msgpick.module.auth.service.AuthService;
import com.msgpick.module.partners.dto.PartnerRegisterRequest;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/phone-verifications/new")
    public CommonResponse registerAuth(@RequestBody AuthVerifiedRequest request) {
        authService.registerAuth(request);
        return CommonResponse.success(null, "휴대폰 인증번호 발송");
    }

    @PostMapping("/phone-verifications/confirm")
    public CommonResponse findVerifications(@RequestBody AuthCheckVerifiedRequest request) {
        authService.findVerifications(request);
        return CommonResponse.success(null, "휴대폰 인증 완료");
    }

    @PostMapping("/sign-up")
    public CommonResponse registerPartner(@RequestBody @Valid PartnerRegisterRequest request) {
        authService.registerPartner(request);
        return CommonResponse.success(null, "파트너 등록 성공");
    }

    @GetMapping("/if-exist-email")
    public CommonResponse checkDuplicatedEmail(@RequestParam("email") String email) {
//        boolean exist = authService.ifExistEmail(email);
//        if (exist) {
//            return CommonResponse.success(true, "이미 사용중인 이메일입니다.");
//        }
        return CommonResponse.success(false, "사용가능");
    }

}
