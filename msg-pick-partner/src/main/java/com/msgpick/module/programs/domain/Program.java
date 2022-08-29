package com.msgpick.module.programs.domain;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "programs")
@NoArgsConstructor
@Entity
public class Program extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;
    private Long shopId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Program(Long shopId, String name, Long price, Long discountedPrice, String description) {
        this.shopId = shopId;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.description = description;
    }
}
