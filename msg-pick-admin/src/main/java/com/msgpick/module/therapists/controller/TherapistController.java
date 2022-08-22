package com.msgpick.module.therapists.controller;

import com.msgpick.infra.security.CustomUserDetails;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.service.TherapistService;
import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/therapists")
public class TherapistController {

    private final TherapistService therapistService;
    private final ShopService shopService;

    @GetMapping("/register")
    public String therapistRegisterPage(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        var shopInfo = shopService.findShop(currentUser.getPartnerId());

        if(shopInfo != null) {
            return "redirect:/shops/register-complete";
        }

        model.addAttribute("nationalities", Nationality.values());
        return "therapists/register";
    }

    @GetMapping("/programs/register")
    public String therapistPage(List<TherapistDetailResponse> registerTherapistList) throws Exception {
        SessionUtil.setAttribute(SessionUtil.REGISTER_THERAPIST_INFO, registerTherapistList);
        return "therapists/register";
    }

    @GetMapping("/list")
    public String therapistListPage(@PathVariable("shopId") Long shopId, Model model) {
        var therapistList = therapistService.findTherapistList(shopId);
        model.addAttribute("therapistList", therapistList);

        return "therapists/edit";
    }

}
