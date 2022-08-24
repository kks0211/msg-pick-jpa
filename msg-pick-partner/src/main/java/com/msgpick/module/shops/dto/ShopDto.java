package com.msgpick.module.shops.dto;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.code.*;

import java.time.LocalDateTime;

public record ShopDto(LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, Long shopId, Long partnerId,
                      Type type, String name, String businessArea, String howToCome, String homeCareArea,
                      String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale,
                      HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment,
                      String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette,
                      ServiceTime serviceTime, Manner manner, String facilities) {

    public static ShopDto of(LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             boolean deleted,
                             Long shopId,
                             Long partnerId,
                             Type type,
                             String name,
                             String businessArea,
                             String howToCome,
                             String homeCareArea,
                             String zonecode,
                             String address,
                             String addressDetail,
                             String contact,
                             Theme theme,
                             Scale scale,
                             HomeCareScale homeCareScale,
                             DayOff dayOff,
                             String openAt,
                             String closeAt,
                             Payment payment,
                             String introduce,
                             String notice,
                             ServiceTarget serviceTarget,
                             Etiquette etiquette,
                             ServiceTime serviceTime,
                             Manner manner,
                             String facilities) {
        return new ShopDto(createdAt, updatedAt, deleted, shopId, partnerId, type, name, businessArea, howToCome, homeCareArea, zonecode, address, addressDetail, contact, theme, scale, homeCareScale, dayOff, openAt, closeAt, payment, introduce, notice, serviceTarget, etiquette, serviceTime, manner, facilities);
    }

    public static ShopDto of(
                             Long partnerId,
                             Type type,
                             String name,
                             String businessArea,
                             String howToCome,
                             String homeCareArea,
                             String zonecode,
                             String address,
                             String addressDetail,
                             String contact,
                             Theme theme,
                             Scale scale,
                             HomeCareScale homeCareScale,
                             DayOff dayOff,
                             String openAt,
                             String closeAt,
                             Payment payment,
                             String introduce,
                             String notice,
                             ServiceTarget serviceTarget,
                             Etiquette etiquette,
                             ServiceTime serviceTime,
                             Manner manner,
                             String facilities) {
        return new ShopDto(null, null, null, null, partnerId, type, name, businessArea, howToCome, homeCareArea, zonecode, address, addressDetail, contact, theme, scale, homeCareScale, dayOff, openAt, closeAt, payment, introduce, notice, serviceTarget, etiquette, serviceTime, manner, facilities);
    }

    public static ShopDto from(Shop entity) {
        return new ShopDto(
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeleted(),
                entity.getShopId(),
                entity.getPartnerId(),
                entity.getType(),
                entity.getName(),
                entity.getBusinessArea(),
                entity.getHowToCome(),
                entity.getHomeCareArea(),
                entity.getZonecode(),
                entity.getAddress(),
                entity.getAddressDetail(),
                entity.getContact(),
                entity.getTheme(),
                entity.getScale(),
                entity.getHomeCareScale(),
                entity.getDayOff(),
                entity.getOpenAt(),
                entity.getCloseAt(),
                entity.getPayment(),
                entity.getIntroduce(),
                entity.getNotice(),
                entity.getServiceTarget(),
                entity.getEtiquette(),
                entity.getServiceTime(),
                entity.getManner(),
                entity.getFacilities());
    }

    public Shop toEntity () {
        return Shop.of(
                partnerId,
                type,
                name,
                businessArea,
                howToCome,
                homeCareArea,
                zonecode,
                address,
                addressDetail,
                contact,
                theme,
                scale,
                homeCareScale,
                dayOff,
                openAt,
                closeAt,
                payment,
                introduce,
                notice,
                serviceTarget,
                etiquette,
                serviceTime,
                manner,
                facilities);
    }

}
