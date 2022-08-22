package com.msgpick.module.admins.controller;

import com.msgpick.module.admins.dto.AdminDetailResponse;
import com.msgpick.module.admins.service.AdminService;
import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.msgpick.code.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String listPage(Model model, @ModelAttribute("params") AdminDetailResponse response) {
        var adminList = adminService.findAdminList(response);
        model.addAttribute("admins", adminList);

        return "admins/list";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("roles", Role.MANAGER);

        return "admins/register";
    }

}
