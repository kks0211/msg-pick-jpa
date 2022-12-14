package com.msgpick.module.shops.dto.request;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.code.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopRegisterRequest {
    @Setter
    private Long partnerId;
    private Type type;
    private String name;
    private String businessArea;
    private String howToCome;
    private String homeCareArea;
    private String zonecode;
    private String address;
    private String addressDetail;
    private String contact;
    private Theme theme;
    private Scale scale;
    //private String businessNumber;
    private HomeCareScale homeCareScale;
    private DayOff dayOff;
    private String openAt;
    private String closeAt;
    private Payment payment;
    private String introduce;
    private String notice;
    private ServiceTarget serviceTarget;
    private Etiquette etiquette;
    private ServiceTime serviceTime;
    private Manner manner;
    private List<Facility> facilities;
    private Status status;
    private String rejectMessage;

    private static String convertFacility(List<Facility> request) {
        return request.stream()
                .map(f -> String.valueOf(f))
                .collect(joining(","));
    }

    public Shop toEntity(Long partnerId) {
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
                convertFacility(facilities),
                status,
                rejectMessage
        );
    }

}
