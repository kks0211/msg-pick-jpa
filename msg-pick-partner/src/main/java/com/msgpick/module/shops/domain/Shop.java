package com.msgpick.module.shops.domain;


import com.msgpick.msgpick.code.*;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "shops")
@NoArgsConstructor
@Entity
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;
    private Long partnerId;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String name;
    private String businessArea;
    private String howToCome;
    private String homeCareArea;
    private String zonecode;
    private String address;
    private String addressDetail;
    private String contact;
    @Enumerated(EnumType.STRING)
    private Theme theme;
    @Enumerated(EnumType.STRING)
    private Scale scale;
    //private String businessNumber;
    @Enumerated(EnumType.STRING)
    private HomeCareScale homeCareScale;
    @Enumerated(EnumType.STRING)
    private DayOff dayOff;
    private String openAt;
    private String closeAt;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private String introduce;
    private String notice;
    @Enumerated(EnumType.STRING)
    private ServiceTarget serviceTarget;
    @Enumerated(EnumType.STRING)
    private Etiquette etiquette;
    @Enumerated(EnumType.STRING)
    private ServiceTime serviceTime;
    @Enumerated(EnumType.STRING)
    private Manner manner;
    private String facilities;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String rejectMessage;

    public Shop (Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, String facilities, Status status, String rejectMessage) {
        this.partnerId = partnerId;
        this.type = type;
        this.name = name;
        this.businessArea = businessArea;
        this.howToCome = howToCome;
        this.homeCareArea = homeCareArea;
        this.zonecode = zonecode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.contact = contact;
        this.theme = theme;
        this.scale = scale;
        this.homeCareScale = homeCareScale;
        this.dayOff = dayOff;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.payment = payment;
        this.introduce = introduce;
        this.notice = notice;
        this.serviceTarget = serviceTarget;
        this.etiquette = etiquette;
        this.serviceTime = serviceTime;
        this.manner = manner;
        this.facilities = facilities;
        this.status = status;
        this.rejectMessage = rejectMessage;
    }

    public static Shop of (Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, String facilities, Status status, String rejectMessage) {
        return new Shop(partnerId, type, name, businessArea, howToCome, homeCareArea, zonecode, address,  addressDetail, contact, theme, scale, homeCareScale, dayOff, openAt, closeAt, payment, introduce, notice, serviceTarget, etiquette, serviceTime, manner, facilities, status, rejectMessage);
    }



}
