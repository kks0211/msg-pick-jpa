package com.msgpick.module.shops.mapper;

import com.msgpick.module.shops.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {
    Long save(ShopRegisterRequest shop);

    ShopDetailResponse findByShopDetail(Long partnerId);

    ShopSummaryResponse findByShopSummary(Long partnerId);

    int update(ShopUpdateRequest request);

    ShopDetailResponse findByUpdateShop(Long shopId);

    void saveImg(@Param("shopId") Long shopId, @Param("imgPath") String imgPath);

    List<ShopImgPathResponse> findByShopImg(Long shopId);

    void deleteImg(Long shopId);
}
