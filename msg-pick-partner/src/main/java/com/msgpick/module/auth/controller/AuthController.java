package com.msgpick.module.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/sign-in")
    public String signInPage() {
        return "partners/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        return "partners/sign-up";
    }

}

