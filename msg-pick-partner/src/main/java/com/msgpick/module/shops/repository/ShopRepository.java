package com.msgpick.module.shops.repository;

import com.msgpick.module.shops.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
