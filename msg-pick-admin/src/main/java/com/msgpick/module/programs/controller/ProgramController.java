package com.msgpick.module.programs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msgpick.infra.security.CustomUserDetails;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
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

    private final ObjectMapper objectMapper;
    private final ShopService shopService;

    @GetMapping("/register")
    public String programRegisterPage(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) throws Exception {

        var shopInfo = shopService.findShop(currentUser.getPartnerId());

        if (shopInfo != null) {
            return "redirect:/shops/register-complete";
        }

        List<ProgramRegisterRequest> programList = (List<ProgramRegisterRequest>) SessionUtil.getAttribute(SessionUtil.REGISTER_PROGRAM_INFO);

        if (programList != null && !programList.isEmpty()) {
            model.addAttribute("sessionPrograms", objectMapper.writeValueAsString(programList));
        }

        return "programs/register";
    }

}
