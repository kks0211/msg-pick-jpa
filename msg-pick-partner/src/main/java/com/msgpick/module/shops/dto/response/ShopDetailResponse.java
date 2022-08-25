package com.msgpick.module.shops.dto.response;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.code.*;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
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
    private Status status;
    private String facilityData;
    private String rejectMessage;
    @Setter
    private String imgPath;

    public ShopDetailResponse(Long shopId, Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, Status status, String facilityData, String rejectMessage, String imgPath) {
        this.shopId = shopId;
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
        this.status = status;
        this.facilityData = facilityData;
        this.rejectMessage = rejectMessage;
        this.imgPath = imgPath;
    }

    public static ShopDetailResponse of(Long shopId, Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, Status status, String facilityData, String rejectMessage, String imgPath) {
        return new ShopDetailResponse(
                shopId,
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
                status,
                facilityData,
                rejectMessage,
                imgPath
        );
    }

    public static ShopDetailResponse from(Shop entity) {
        return new ShopDetailResponse(
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
                entity.getsta()


        );
    }

}
