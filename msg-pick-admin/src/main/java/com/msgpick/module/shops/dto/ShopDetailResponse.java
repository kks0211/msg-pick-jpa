package com.msgpick.module.shops.dto;

import com.msgpick.msgpick.code.*;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ShopDetailResponse extends BaseEntity {
    private Long shopId;
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
    @Setter
    private List<Facility> facilities;
    private Double latitude;
    private Double longitude;
    private String facilityData;
    private Status status;
}
