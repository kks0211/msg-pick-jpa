package com.msgpick.module.shops.controller;

import com.msgpick.module.shops.dto.ShopRegisterRequest;
import com.msgpick.module.shops.dto.ShopRejectRequest;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shops")
public class ShopApiController {

    private final ShopService shopService;

    @PostMapping("/register")
    public CommonResponse registerShopApi(@RequestBody @Valid ShopRegisterRequest registerShopRequest) throws Exception {

        SessionUtil.setAttribute(SessionUtil.REGISTER_SHOP_INFO, registerShopRequest);

        return CommonResponse.success(null);
    }

    @PatchMapping(value = {"/reject/{shopId}", "/reject"})
    public CommonResponse registerRejectShopApi(@PathVariable(required = false) Long shopId, @RequestBody ShopRejectRequest rejectRequest) {

        shopService.registerRejectShop(shopId, rejectRequest);

        return CommonResponse.success(null);
    }

    @PatchMapping(value = {"/approval/{shopId}", "/approval"})
    public CommonResponse registerApprovalShopApi(@PathVariable(required = false) Long shopId) {

        shopService.registerApprovalShop(shopId);

        return CommonResponse.success(null);
    }

}
