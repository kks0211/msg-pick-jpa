package com.msgpick.module.shops.dto.request;

import com.msgpick.module.shops.dto.ShopDto;
import com.msgpick.msgpick.code.*;

import java.time.LocalDateTime;

public record ShopRegisterRequestDto(Long partnerId,
                                     Type type, String name, String businessArea, String howToCome, String homeCareArea,
                                     String zonecode, String address, String addressDetail, String contact, Theme theme,
                                     Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt,
                                     String closeAt, Payment payment, String introduce, String notice,
                                     ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime,
                                     Manner manner, String facilities) {

    public static ShopRegisterRequestDto of(Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, String facilities) {
        return new ShopRegisterRequestDto(partnerId, type, name, businessArea, howToCome, homeCareArea, zonecode, address, addressDetail, contact, theme, scale, homeCareScale, dayOff, openAt, closeAt, payment, introduce, notice, serviceTarget, etiquette, serviceTime, manner, facilities);
    }

    public ShopDto toDto() {
        return ShopDto.of(
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
