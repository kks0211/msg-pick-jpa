package com.msgpick.module.shops.repository;

import com.msgpick.module.shops.domain.ShopImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopImgRepository extends JpaRepository<ShopImg, Long> {
    List<ShopImg> findAllByShopId(Long shopId);

}
