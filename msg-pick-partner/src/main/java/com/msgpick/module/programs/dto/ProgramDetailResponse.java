package com.msgpick.module.programs.dto;

import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDetailResponse extends BaseEntity {
    private Long programId;
    private Long shopId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;
}
