package com.msgpick.module.shops.service;


import com.msgpick.module.shops.dto.ShopDetailResponse;
import com.msgpick.module.shops.dto.ShopRejectRequest;
import com.msgpick.module.shops.dto.UnConfirmedShopsResponse;
import com.msgpick.module.shops.mapper.ShopMapper;
import com.msgpick.msgpick.code.Facility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopMapper shopMapper;

    @Transactional(readOnly = true)
    public List<ShopDetailResponse> findShopList() {
        var shopInfo = shopMapper.findByShopList();

        if (shopInfo.size() == 0) {
            return null;
        }

        IntStream.range(0, shopInfo.size()).forEach(i -> {
            var shopFacilityList = Arrays.stream(shopInfo.get(i).getFacilityData().split(","))
                    .map(Facility::valueOf)
                    .collect(Collectors.toList());
            shopInfo.get(i).setFacilities(shopFacilityList);
        });

        return shopInfo;
    }

    @Transactional(readOnly = true)
    public ShopDetailResponse findShop(Long shopId) {
        var shopInfo = shopMapper.findShop(shopId);

        if (shopInfo == null) {
            return null;
        }

        var shopFacility = shopInfo.getFacilityData();
        List<Facility> shopFacilityList = Arrays.stream(shopFacility.split(","))
                .map(Facility::valueOf)
                .collect(Collectors.toList());
        shopInfo.setFacilities(shopFacilityList);

        return shopInfo;
    }

    @Transactional(readOnly = true)
    public List<UnConfirmedShopsResponse> findByEntryStores() {
        var unConfirmedShops = shopMapper.findByEntryStores();

        if (unConfirmedShops == null || unConfirmedShops.isEmpty()) {
            return Collections.emptyList();
        }

        return unConfirmedShops;
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerRejectShop(Long shopId, ShopRejectRequest rejectRequest) {
        shopMapper.saveReject(shopId, rejectRequest.getRejectMessage());
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerApprovalShop(Long shopId) {
        shopMapper.saveApproval(shopId);
    }

}
