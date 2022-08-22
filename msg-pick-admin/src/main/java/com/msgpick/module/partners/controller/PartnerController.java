package com.msgpick.module.partners.controller;

import com.msgpick.module.partners.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    public String partnerListPage(Model model) {
        var partnerList = partnerService.findByPartnerList();
        model.addAttribute("partnerList", partnerList);
        return "/";
    }

    @GetMapping("/{partnerId}")
    public String partnerListPage(@PathVariable("partnerId") Long partnerId, Model model) {
        var partnerInfo = partnerService.findByPartner(partnerId);
        model.addAttribute("partnerList", partnerInfo);
        return "/";
    }

}
