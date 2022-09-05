package com.msgpick.module.therapists.domain;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "therapists")
@NoArgsConstructor
@Entity
public class Therapist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id")
    private Long id;

    private String name;
    private String position;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Therapist(Shop shop, String name, String position, Nationality nationality, String description) {
        this.shop = shop;
        this.name = name;
        this.position = position;
        this.nationality = nationality;
        this.description = description;
    }
}
