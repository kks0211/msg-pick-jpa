package com.msgpick.module.shops.mapper;

import com.msgpick.module.shops.dto.*;
import com.msgpick.module.shops.dto.request.ShopRegisterRequest;

import java.util.List;

public interface ShopMapper {
    Long save(ShopRegisterRequest shop);

    ShopDetailResponse findByShopDetail(Long partnerId);

    ShopSummaryResponse findByShopSummary(Long partnerId);

    int update(ShopUpdateRequest request);

    ShopDetailResponse findByUpdateShop(Long shopId);

    void saveImg(Long shopId, String imgPath);

    List<ShopImgPathResponse> findByShopImg(Long shopId);

    void deleteImg(Long shopId);
}
