package com.msgpick.module.admins.controller;

import com.msgpick.module.admins.service.AdminService;
import com.msgpick.msgpick.code.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping()
    public String listPage(Model model) {
        var adminList = adminService.findAdminList();
        model.addAttribute("admins", adminList);

        return "admins/list";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("roles", Role.MANAGER);

        return "admins/register";
    }

}
