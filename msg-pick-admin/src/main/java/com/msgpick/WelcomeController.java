package com.msgpick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping
    public String signInPage() {
        return "admins/sign-in";
    }

}
