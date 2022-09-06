package com.msgpick.module.shops.domain;

import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "shops_imgs")
@NoArgsConstructor
@Entity
public class ShopImg extends BaseEntity {

    @Id
    @Column(name = "shop_id")
    private Long id;

    private String img_path;

    @Builder
    public ShopImg(Long id, String img_path) {
        this.id = id;
        this.img_path = img_path;
    }
}
