package com.msgpick.module.shops.dto.request;

import com.msgpick.msgpick.code.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopUpdateRequest {

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
    private String businessNumber;
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



    /*public Shop toEntity() {
        return Shop.builder()
                .type(type)
                .name(name)
                .businessArea(businessArea)
                .howToCome(howToCome)
                .homeCareArea(homeCareArea)
                .zonecode(zonecode)
                .address(address)
                .addressDetail(addressDetail)
                .contact(contact)
                .theme(theme)
                .scale(scale)
                .homeCareScale(homeCareScale)
                .dayOff(dayOff)
                .openAt(openAt)
                .closeAt(closeAt)
                .payment(payment)
                .introduce(introduce)
                .notice(notice)
                .etiquette(etiquette)
                .serviceTarget(serviceTarget)
                .serviceTime(serviceTime)
                .manner(manner)
                .facilities(convertFacility(facilities))
                .build();
    }*/

}
