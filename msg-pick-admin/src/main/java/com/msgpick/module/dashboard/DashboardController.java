package com.msgpick.module.dashboard;

import com.msgpick.infra.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal CustomUserDetails currentUser) {

        return "dashboard";

    }

}
