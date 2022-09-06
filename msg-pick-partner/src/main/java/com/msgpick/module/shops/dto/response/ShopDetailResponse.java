package com.msgpick.module.shops.dto.response;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.module.shops.domain.ShopImg;
import com.msgpick.msgpick.code.*;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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
    private List<Facility> facilities;
    private Status status;
    private String rejectMessage;
    private String imgPath;

    @Builder
    public ShopDetailResponse(Long shopId, Long partnerId, Type type, String name, String businessArea, String howToCome, String homeCareArea, String zonecode, String address, String addressDetail, String contact, Theme theme, Scale scale, HomeCareScale homeCareScale, DayOff dayOff, String openAt, String closeAt, Payment payment, String introduce, String notice, ServiceTarget serviceTarget, Etiquette etiquette, ServiceTime serviceTime, Manner manner, List<Facility> facilities, Status status, String rejectMessage, String imgPath) {
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
        this.facilities = facilities;
        this.status = status;
        this.rejectMessage = rejectMessage;
        this.imgPath = imgPath;
    }

    private static List<Facility> convertFacility(String shopFacility) {
        return Arrays.stream(shopFacility.split(","))
                .map(Facility::valueOf)
                .collect(Collectors.toList());
    }

    private static String convertImgPath(List<ShopImg> shopImgPathList) {
        return shopImgPathList.stream()
                .map(n -> n.getImg_path())
                .collect(joining(","));
    }

    public static ShopDetailResponse toDto(Shop entity, List<ShopImg> shopImgPathList) {
        return ShopDetailResponse.builder()
                .shopId(entity.getId())
                .partnerId(entity.getPartnerId())
                .type(entity.getType())
                .name(entity.getName())
                .businessArea(entity.getBusinessArea())
                .howToCome(entity.getHowToCome())
                .homeCareArea(entity.getHomeCareArea())
                .zonecode(entity.getZonecode())
                .address(entity.getAddress())
                .addressDetail(entity.getAddressDetail())
                .contact(entity.getContact())
                .theme(entity.getTheme())
                .scale(entity.getScale())
                .homeCareScale(entity.getHomeCareScale())
                .dayOff(entity.getDayOff())
                .openAt(entity.getOpenAt())
                .closeAt(entity.getCloseAt())
                .payment(entity.getPayment())
                .introduce(entity.getIntroduce())
                .notice(entity.getNotice())
                .etiquette(entity.getEtiquette())
                .serviceTarget(entity.getServiceTarget())
                .serviceTime(entity.getServiceTime())
                .manner(entity.getManner())
                .facilities(convertFacility(entity.getFacilities()))
                .status(entity.getStatus())
                .rejectMessage(entity.getRejectMessage())
                .imgPath(convertImgPath(shopImgPathList))
                .build();
    }


}
