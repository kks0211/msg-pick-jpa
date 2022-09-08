package com.msgpick.module.shops.controller;

import com.msgpick.infra.security.PartnerPrincipal;
import com.msgpick.module.programs.service.ProgramService;
import com.msgpick.module.shops.dto.request.ShopRegisterRequest;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.module.therapists.service.TherapistService;
import com.msgpick.msgpick.code.*;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ProgramService programService;
    private final TherapistService therapistService;

    @GetMapping("/register")
    public String shopRegisterPage(Model model, RedirectAttributes rttr, @AuthenticationPrincipal PartnerPrincipal currentUser,
                                   @ModelAttribute("params") TherapistDetailResponse therapistDetailResponse) throws Exception {
        var shopInfo = shopService.findShopDetail(currentUser.id());

        if (shopInfo != null && shopInfo.getStatus() == Status.REVIEW) {
            return "redirect:/shops/register-complete";
        }

        model.addAttribute("types", Type.values());
        model.addAttribute("themes", Theme.values());
        model.addAttribute("scales", Scale.values());
        model.addAttribute("homeCareScales", HomeCareScale.values());
        model.addAttribute("facilities", Facility.values());
        model.addAttribute("payments", Payment.values());
        model.addAttribute("dayOffs", DayOff.values());
        model.addAttribute("serviceTargets", ServiceTarget.values());
        model.addAttribute("etiquetteList", Etiquette.values());
        model.addAttribute("serviceTimes", ServiceTime.values());
        model.addAttribute("manners", Manner.values());

        /*if (shopInfo != null && shopInfo.getStatus() == Status.REJECT) {
            model.addAttribute("sessionShop", shopInfo);
            var programList = programService.findProgramList(shopInfo.getShopId());
            SessionUtil.setAttribute(SessionUtil.REGISTER_PROGRAM_INFO, programList);
            var therapistList = therapistService.findTherapistList(shopInfo.getShopId());
            //var therapistList = therapistService.findTherapistListPage(therapistDetailResponse);
            SessionUtil.setAttribute(SessionUtil.REGISTER_THERAPIST_INFO, therapistList);
            rttr.addFlashAttribute("reject", shopInfo.getRejectMessage());
            return "/shops/register";
        }*/

        ShopRegisterRequest sessionShop = (ShopRegisterRequest) SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_INFO);
        var sessionImg = SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_IMG_INFO);

        if (sessionShop != null) {
            model.addAttribute("sessionShop", sessionShop);
        }

        if (sessionImg != null) {
            var img = (MultiValueMap<String, MultipartFile>) sessionImg;
            List<MultipartFile> multipartFileList = new ArrayList<>();

            img.entrySet().stream()
                    .filter(f -> !img.isEmpty())
                    .map(Map.Entry::getValue)
                    .collect(groupingBy(multipartFileList::addAll));
            model.addAttribute("sessionImg", multipartFileList);
        }

        return "shops/register";
    }

    @GetMapping("/register-complete")
    public String registerCompletePage(@AuthenticationPrincipal PartnerPrincipal currentUser) {
        var shopInfo = shopService.findShopSummary(currentUser.id());

        if (shopInfo != null && shopInfo.getStatus() == Status.REVIEW) {
            return "shops/register-complete";

        }
        return "redirect:/dashboard";
    }

    @GetMapping("/modify")
    public String modifyShopPage(@AuthenticationPrincipal PartnerPrincipal currentUser, Model model) {
        var shopInfo = shopService.findShopDetail(currentUser.id());

        model.addAttribute("shopInfo", shopInfo);
        model.addAttribute("types", Type.values());
        model.addAttribute("themes", Theme.values());
        model.addAttribute("scales", Scale.values());
        model.addAttribute("homeCareScales", HomeCareScale.values());
        model.addAttribute("facilities", Facility.values());
        model.addAttribute("payments", Payment.values());
        model.addAttribute("dayOffs", DayOff.values());
        model.addAttribute("serviceTargets", ServiceTarget.values());
        model.addAttribute("etiquetteList", Etiquette.values());
        model.addAttribute("serviceTimes", ServiceTime.values());
        model.addAttribute("manners", Manner.values());

        return "shops/modify";
    }

    @GetMapping
    public String shopInfoPage(@AuthenticationPrincipal PartnerPrincipal currentUser, Model model) {
        var shopInfo = shopService.findShopDetail(currentUser.id());
        //var programs = programService.findProgramList(shopInfo.getShopId());
        //var therapists = therapistService.findTherapistList(shopInfo.getShopId());

        model.addAttribute("shopInfo", shopInfo);
        model.addAttribute("programs", shopInfo.getProgramList());
        model.addAttribute("therapists", shopInfo.getTherapistList());

        return "shops/shop-info";
    }

}
