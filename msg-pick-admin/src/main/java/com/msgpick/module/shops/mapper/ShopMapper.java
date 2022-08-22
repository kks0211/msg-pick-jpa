package com.msgpick.module.shops.mapper;


import com.msgpick.module.shops.dto.ShopDetailResponse;
import com.msgpick.module.shops.dto.UnConfirmedShopsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {
    List<ShopDetailResponse> findByShopList();

    ShopDetailResponse findShop(Long shopId);

    List<UnConfirmedShopsResponse> findByEntryStores();

    void saveReject(@Param("shopId") Long shopId, @Param("rejectMessage") String rejectMessage);

    void saveApproval(Long shopId);
}
