package com.msgpick.module.programs.domain;

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
    private Long programId;
    private Long shopId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;

}
