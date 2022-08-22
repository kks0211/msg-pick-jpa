package com.msgpick.module.therapists.controller;

import com.msgpick.module.programs.service.ProgramService;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/therapists")
public class TherapistApiController {

    private final ShopService shopService;
    private final ProgramService programService;
    private final TherapistService therapistService;

}
