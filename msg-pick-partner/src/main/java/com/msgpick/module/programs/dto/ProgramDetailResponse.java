package com.msgpick.module.programs.dto;

import com.msgpick.module.programs.domain.Program;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramDetailResponse extends BaseEntity {
    private Long id;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;

    @Builder
    public ProgramDetailResponse(Long id, String name, Long price, Long discountedPrice, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.description = description;
    }

    public static ProgramDetailResponse toDto(Program entity) {
        return ProgramDetailResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .discountedPrice(entity.getDiscountedPrice())
                .description(entity.getDescription())
                .build();
    }

}
