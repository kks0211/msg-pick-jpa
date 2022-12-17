package com.msgpick.module.shops.controller;

import com.msgpick.module.shops.dto.request.ShopRegisterRequest;
import com.msgpick.module.shops.dto.request.ShopUpdateRequest;
import com.msgpick.module.shops.service.ShopService;
import com.msgpick.msgpick.global.common.response.CommonResponse;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shops")
public class ShopApiController {

    private final ShopService shopService;

    @PostMapping(value = "/register")
    public CommonResponse registerShopApi(@RequestPart(name = "payload") ShopRegisterRequest shopRegisterRequest,
                                          @RequestPart(name = "files") List<MultipartFile> files) throws Exception {

        SessionUtil.setAttribute(SessionUtil.REGISTER_SHOP_INFO, shopRegisterRequest);
        SessionUtil.setAttribute(SessionUtil.REGISTER_SHOP_IMG_INFO, files);

        return CommonResponse.success(null);
    }

    @PutMapping("/{shopId}")
    public CommonResponse modifyShopApi(@PathVariable("shopId") Long shopId,
                                        @RequestPart(name = "payload") ShopUpdateRequest shopUpdateRequest,
                                        @RequestPart(name = "files") List<MultipartFile> files) {


        shopService.modifyShop(shopId, shopUpdateRequest, files);

        var findModifyShop = shopService.findModifyShop(shopId);


        return CommonResponse.success(findModifyShop);
    }

}
