package com.msgpick.module.shops.dto.response;

import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.shops.domain.Shop;
import com.msgpick.module.shops.domain.ShopImg;
import com.msgpick.module.therapists.dto.TherapistDetailResponse;
import com.msgpick.msgpick.code.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Getter
@NoArgsConstructor
public class ShopDetailResponse {
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    private List<ProgramDetailResponse> programList;
    private List<TherapistDetailResponse> therapistList;

    @Builder
    public ShopDetailResponse(Long shopId,
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
                              List<Facility> facilities,
                              Status status,
                              String rejectMessage,
                              String imgPath,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              Boolean deleted,
                              List<ProgramDetailResponse> programList,
                              List<TherapistDetailResponse> therapistList) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.programList = programList;
        this.therapistList = therapistList;
    }

    private static List<Facility> convertFacility(String shopFacility) {
        if (StringUtils.isEmpty(shopFacility)) {
            return Collections.emptyList();
        }
        return Arrays.stream(shopFacility.split(","))
                .map(Facility::valueOf)
                .collect(Collectors.toList());
    }

    private static String convertImgPath(List<ShopImg> shopImgPathList) {
        if (shopImgPathList.isEmpty()) {
            return "";
        }
        return shopImgPathList.stream()
                .map(n -> n.getImg_path())
                .collect(joining(","));
    }

    public static ShopDetailResponse toDto(Shop entity,
                                           List<ProgramDetailResponse> programInfo,
                                           List<TherapistDetailResponse> therapistInfo,
                                           List<ShopImg> shopImgPathList) {
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
                .programList(programInfo)
                .therapistList(therapistInfo)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deleted(entity.getDeleted())
                .build();
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
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deleted(entity.getDeleted())
                .build();
    }

}
