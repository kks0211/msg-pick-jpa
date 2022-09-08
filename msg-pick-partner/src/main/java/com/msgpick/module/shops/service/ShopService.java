package com.msgpick.module.shops.service;

import com.google.common.io.Files;
import com.msgpick.module.programs.dto.ProgramDetailResponse;
import com.msgpick.module.programs.dto.ProgramRegisterRequest;
import com.msgpick.module.programs.repository.ProgramRepository;
import com.msgpick.module.shops.domain.ShopImg;
import com.msgpick.module.shops.dto.request.ShopRegisterRequest;
import com.msgpick.module.shops.dto.request.ShopUpdateRequest;
import com.msgpick.module.shops.dto.response.ShopDetailResponse;
import com.msgpick.module.shops.dto.response.ShopSummaryResponse;
import com.msgpick.module.shops.repository.ShopImgRepository;
import com.msgpick.module.shops.repository.ShopRepository;
import com.msgpick.module.therapists.dto.TherapistRegisterRequest;
import com.msgpick.module.therapists.repository.TherapistRepository;
import com.msgpick.msgpick.global.common.exception.EntityNotFoundException;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ShopImgRepository shopImgRepository;
    private final ProgramRepository programRepository;
    private final TherapistRepository therapistRepository;

    @Value("${file.upload-path}")
    private String FILE_UPLOAD_PATH;

    @Transactional(rollbackFor = Exception.class)
    public void entryStore(Long partnerId, Object shopSession, Object programSession,
                           @NotNull List<TherapistRegisterRequest> registerTherapistList) throws Exception {

        //  shop
        ShopRegisterRequest requestShop = (ShopRegisterRequest) shopSession;
        var shopId = shopRepository.save(requestShop.toEntity(partnerId)).getId();

        //  shopImg
        List<MultipartFile> imgSession = (List<MultipartFile>) SessionUtil.getAttribute(SessionUtil.REGISTER_SHOP_IMG_INFO);
        if (imgSession.size() > 0) {
            shopImg(shopId, imgSession);
        }

        //  program
        List<ProgramRegisterRequest> requestProgram = (List<ProgramRegisterRequest>) programSession;
        var ofProgram = requestProgram.stream()
                .map(program -> program.toEntity())
                .collect(Collectors.toList());

        programRepository.saveAll(ofProgram);


        //  Therapist
        var ofTherapist = registerTherapistList.stream()
                .map(Therapist -> Therapist.toEntity())
                .collect(Collectors.toList());

        therapistRepository.saveAll(ofTherapist);

    }

    @Transactional(readOnly = true)
    public ShopDetailResponse findShopDetail(Long partnerId) {

        //var shopDetail = shopMapper.findByShopDetail(partnerId);
        var shopYN = shopRepository.findByPartnerId(partnerId).isPresent();

        if (shopYN) {
            var shop = shopRepository.findByPartnerId(partnerId)
                    .orElseThrow(() -> new EntityNotFoundException("해당 샵이 없습니다 : " + partnerId));

            var shopImg = shopImgRepository.findAllByShopId(shop.getId());

            var programInfo = shop.getProgramList().stream().map(ProgramDetailResponse::toDto).collect(Collectors.toList());

            var therapistInfo = shop.getTherapistList();

            return ShopDetailResponse.toDto(shop, programInfo, therapistInfo, shopImg);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public ShopSummaryResponse findShopSummary(Long partnerId) {
        var result = shopRepository.findByPartnerId(partnerId)
                .map(ShopSummaryResponse::toDto)
                .isPresent();

        if (!result) {
            return null;
        } else {
            return shopRepository.findByPartnerId(partnerId)
                    .map(ShopSummaryResponse::toDto)
                    .orElseThrow(() -> new EntityNotFoundException("해당 샵이 없습니다 : " + partnerId));
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyShop(Long shopId, ShopUpdateRequest request, List<MultipartFile> updateImg) {

        var checkImg = shopImgRepository.findAllById(Collections.singleton(shopId));

        if (checkImg.size() > 0) {
            shopImgRepository.deleteById(shopId);
            //shopMapper.deleteImg(shopId);
        }

        if (updateImg.size() > 0) {
            shopImg(shopId, updateImg);
        }

        var findShop = shopRepository.getReferenceById(shopId);

        if (findShop != null) {
            findShop.update(request);
        }

        //return shopMapper.update(request);
    }

    @Transactional(readOnly = true)
    public ShopDetailResponse findModifyShop(Long shopId) {

        var shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("해당 샵이 없습니다 : " + shopId));

        var shopImg = shopImgRepository.findAllById(Collections.singleton(shop.getId()));

        return ShopDetailResponse.toDto(shop, shopImg);

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
                }).forEach(s ->
                        shopImgRepository.save(
                                ShopImg.builder()
                                        .shopId(shopId)
                                        .img_path(imgPath + s)
                                        .build()));
        //}).forEach(s -> shopMapper.saveImg(shopId, imgPath + s));
    }

}
