package com.msgpick.module.therapists.controller;

import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.dto.TherapistUpdateRequest;
import com.msgpick.module.therapists.service.TherapistService;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/therapists")
public class TherapistApiController {

    private final ShopService shopService;
    private final TherapistService therapistService;

    @PostMapping("/register")
    public CommonResponse registerTherapistApi(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody @Valid List<TherapistRegisterRequest> registerTherapistList) throws Exception {

        var shopSession = SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_INFO);
        var programSession = SessionUtil.getAttribute(SessionUtil.REGISTER_PROGRAM_INFO);

        shopService.entryStore(customUserDetails.getPartnerId(), shopSession, programSession, registerTherapistList);

        SessionUtil.removeAttribute(SessionUtil.REGISTER_SHOP_INFO);
        SessionUtil.removeAttribute(SessionUtil.REGISTER_SHOP_IMG_INFO);
        SessionUtil.removeAttribute(SessionUtil.REGISTER_PROGRAM_INFO);

        return CommonResponse.success(null);
    }

    @PutMapping(value = {"/{therapistId}", "/"})
    public CommonResponse modifyTherapistApi(@PathVariable(required = false) Long therapistId, @RequestBody @Valid TherapistUpdateRequest therapist) {

        therapistService.modifyTherapist(therapistId, therapist);

        var modifyTherapistInfo = therapistService.findTherapist(therapist.getTherapistId());

        return CommonResponse.success(modifyTherapistInfo);

    }

    @PatchMapping(value = {"/{therapistId}", "/"})
    public CommonResponse removeTherapistApi(@PathVariable("therapistId") Long therapistId) {

        therapistService.removeTherapist(therapistId);

        return CommonResponse.success(null);
    }

}
