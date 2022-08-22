package com.msgpick.module.shops.controller;

import com.msgpick.module.programs.service.ProgramService;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ProgramService programService;
    private final TherapistService therapistService;

    @GetMapping
    public String ShopListPage(Model model) {
        var shopInfoList = shopService.findShopList();

        if (shopInfoList == null) {
            return "dashboard";
        }

        model.addAttribute("shopInfo", shopInfoList);

        return "shops/shopInfo";
    }


    /**
     * 입점신청 한 샵 목록 조회
     */
    @GetMapping("/un-confirmed")
    public String entryStoreConfirmedPage(Model model) {
        var unConfirmedShops = shopService.findByEntryStores();

        model.addAttribute("unConfirmedShops", unConfirmedShops);
        return "shops/un-confirmed-list";
    }


    @GetMapping("/{shopId}")
    public String ShopPage(@PathVariable("shopId") Long shopId, Model model) {
        var shopInfo = shopService.findShop(shopId);
        var programInfo = programService.findProgramList(shopId);
        var therapistInfo = therapistService.findTherapistList(shopId);

        model.addAttribute("shopInfo", shopInfo);
        model.addAttribute("programInfo", programInfo);
        model.addAttribute("therapistInfo", therapistInfo);

        return "shops/shopInfo";
    }

}
