package com.msgpick.module.dashboard;

import com.msgpick.module.shops.service.ShopService;
import com.msgpick.msgpick.code.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ShopService shopService;

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal CustomUserDetails currentUser) {

        var shopInfo = shopService.findShopSummary(currentUser.getPartnerId());

        if (shopInfo == null) {
            return "redirect:/shops/register";
        }

        if (shopInfo != null && shopInfo.getStatus() == Status.REVIEW) {
            return "redirect:/shops/register-complete";
        }

        if (shopInfo != null && shopInfo.getStatus() == Status.REJECT) {
            return "redirect:/shops/modify";
        }

        return "dashboard";
    }

}
