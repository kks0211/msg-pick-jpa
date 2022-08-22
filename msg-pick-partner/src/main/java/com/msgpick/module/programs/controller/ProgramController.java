package com.msgpick.module.programs.controller;

import com.msgpick.infra.security.CustomUserDetails;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.service.ProgramService;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ShopService shopService;
    private final ProgramService programService;

    @GetMapping("/register")
    public String programRegisterPage(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) throws Exception {

        var shopInfo = SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_INFO);

        if (shopInfo == null) {
            return "redirect:/shops/register";
        }

        List<ProgramRegisterRequest> sessionPrograms = (List<ProgramRegisterRequest>) SessionUtil.getAttribute(SessionUtil.REGISTER_PROGRAM_INFO);
        if (sessionPrograms != null && sessionPrograms.size() > 0) {
            model.addAttribute("sessionPrograms", sessionPrograms);
        }

        return "programs/register";
    }

    @GetMapping("/modify")
    public String programModifyPage(@AuthenticationPrincipal CustomUserDetails currentUser, Model model) {
        var shopInfo = shopService.findShopDetail(currentUser.getPartnerId());
        var programs = programService.findProgramList(shopInfo.getShopId());

        model.addAttribute("shopInfo", shopInfo);
        model.addAttribute("programs", programs);

        return "programs/modify";
    }

}
