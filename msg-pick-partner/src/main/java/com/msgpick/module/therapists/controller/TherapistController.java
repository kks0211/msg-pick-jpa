package com.msgpick.module.therapists.controller;

import com.msgpick.infra.security.PartnerPrincipal;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.service.TherapistService;
import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/therapists")
public class TherapistController {

    private final ShopService shopService;
    private final TherapistService therapistService;

    @GetMapping("/register")
    public String therapistRegisterPage(Model model, @AuthenticationPrincipal PartnerPrincipal currentUser) throws Exception {

        List<ProgramRegisterRequest> programList = (List<ProgramRegisterRequest>) SessionUtil.getAttribute(SessionUtil.REGISTER_PROGRAM_INFO);

        if (programList == null || programList.isEmpty()) {
            return "redirect:/programs/register";
        }

        /*List<TherapistRegisterRequest> sessionTherapists = (List<TherapistRegisterRequest>) SessionUtil.getAttribute(SessionUtil.REGISTER_THERAPIST_INFO);
        if (sessionTherapists != null && sessionTherapists.size() > 0) {
            model.addAttribute("sessionTherapists", sessionTherapists);
        }*/

        model.addAttribute("nationalities", Nationality.values());
        return "therapists/register";
    }

    @GetMapping("/modify")
    public String modifyTherapistPage(@AuthenticationPrincipal PartnerPrincipal currentUser, Model model) {
        var shopInfo = shopService.findShopDetail(currentUser.id());
        var therapists = therapistService.findTherapistList(shopInfo.get().getShopId());

        model.addAttribute("shopInfo", shopInfo.get());
        model.addAttribute("therapists", therapists);
        model.addAttribute("nationalities", Nationality.values());

        return "therapists/modify";
    }

}
