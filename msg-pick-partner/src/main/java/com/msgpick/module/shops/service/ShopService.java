package com.msgpick.module.shops.service;

import com.google.common.io.Files;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.mapper.ProgramMapper;
import com.msgpick.module.shops.dto.ShopDetailResponse;
import com.msgpick.module.shops.dto.request.ShopRegisterRequest;
import com.msgpick.module.shops.dto.ShopSummaryResponse;
import com.msgpick.module.shops.dto.ShopUpdateRequest;
import com.msgpick.module.shops.mapper.ShopMapper;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.mapper.TherapistMapper;
import com.msgpick.msgpick.code.Facility;
import com.msgpick.msgpick.utils.FileUtil;
import com.msgpick.msgpick.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopMapper shopMapper;
    private final ProgramMapper programMapper;
    private final TherapistMapper therapistMapper;

    @Value("${file.upload-path}")
    private String FILE_UPLOAD_PATH;

    @Transactional(rollbackFor = Exception.class)
    public void entryStore(Long partnerId, Object shopSession, Object programSession,
                           @NotNull List<TherapistRegisterRequest> registerTherapistList) throws Exception {

        //  shop
        ShopRegisterRequest requestShop = (ShopRegisterRequest) shopSession;
        requestShop.setPartnerId(partnerId);

        String facilityData = requestShop.getFacilities()
                .stream()
                .collect(joining(","));
        requestShop.setFacilityData(facilityData);

        shopMapper.save(requestShop);

        //  shopImg
        List<MultipartFile> imgSession = (List<MultipartFile>) SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_IMG_INFO);
        if (imgSession.size() > 0) {
            shopImg(requestShop.getShopId(), imgSession);
        }

        //  program
        List<ProgramRegisterRequest> requestProgram = (List<ProgramRegisterRequest>) programSession;
        var ofProgram = requestProgram.stream()
                .map(program -> {
                    program.setShopId(requestShop.getShopId());
                    return program;
                }).collect(Collectors.toList());

        programMapper.saveAll(ofProgram);

        //  Therapist
        var ofTherapist = registerTherapistList.stream()
                .map(Therapist -> {
                    Therapist.setShopId(requestShop.getShopId());
                    return Therapist;
                }).collect(Collectors.toList());

        therapistMapper.saveAll(ofTherapist);

    }

    @Transactional(readOnly = true)
    public ShopDetailResponse findShopDetail(Long partnerId) {

        var shopDetail = shopMapper.findByShopDetail(partnerId);

        if (shopDetail == null) {
            return null;
        }

        var shopFacility = shopDetail.getFacilityData();
        List<Facility> shopFacilityList = Arrays.stream(shopFacility.split(","))
                .map(Facility::valueOf)
                .collect(Collectors.toList());
        shopDetail.setFacilities(shopFacilityList);

        var shopImgPathList = shopMapper.findByShopImg(shopDetail.getShopId());
        String shopImgPath = shopImgPathList.stream()
                .map(n -> n.getImgPath())
                .collect(joining(","));
        shopDetail.setImgPath(shopImgPath);

        return shopDetail;
    }

    @Transactional(readOnly = true)
    public ShopSummaryResponse findShopSummary(Long partnerId) {

        var shopSummary = shopMapper.findByShopSummary(partnerId);

        if (shopSummary == null) {
            return null;
        }

        return shopSummary;
    }

    @Transactional(rollbackFor = Exception.class)
    public int modifyShop(Long shopId, ShopUpdateRequest request, List<MultipartFile> updateImg) {

        var checkImg = shopMapper.findByShopImg(shopId);
        if (checkImg.size() > 0) {
            shopMapper.deleteImg(shopId);
        }

        if (updateImg.size() > 0) {
            shopImg(shopId, updateImg);
        }

        String facilityData = request.getFacilities()
                .stream()
                .collect(joining(","));
        request.setFacilityData(facilityData);

        request.setShopId(shopId);

        return shopMapper.update(request);
    }

    @Transactional(readOnly = true)
    public ShopDetailResponse findModifyShop(Long shopId) {

        var result = shopMapper.findByUpdateShop(shopId);
        var shopFacility = result.getFacilityData();
        List<Facility> shopFacilityList = Arrays.stream(shopFacility.split(","))
                .map(Facility::valueOf)
                .collect(Collectors.toList());
        result.setFacilities(shopFacilityList);

        var shopImgPathList = shopMapper.findByShopImg(shopId);

        String shopImgPath = shopImgPathList.stream()
                .map(n -> n.getImgPath())
                .collect(joining(","));
        result.setImgPath(shopImgPath);

        return result;
    }

    public void shopImg(Long shopId, List<MultipartFile> img) {

        var imgPath = FileUtil.pathCheck();
        var folder = FileUtil.folderCheck(FILE_UPLOAD_PATH, imgPath);

        img.stream()
                .filter(t -> Files.getFileExtension(t.getOriginalFilename()) != "")
                .map(s -> {
                    String path = File.separator + UUID.randomUUID() + "." + Files.getFileExtension(s.getOriginalFilename());
                    try {
                        s.transferTo(new File(folder + path));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return path;
                }).forEach(s -> shopMapper.saveImg(shopId, imgPath + s));
    }

}
