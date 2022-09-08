package com.msgpick.module.programs.dto;

import com.msgpick.module.programs.domain.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProgramDetailResponse {
    private Long id;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    @Builder
    public ProgramDetailResponse(Long id,
                                 String name,
                                 Long price,
                                 Long discountedPrice,
                                 String description,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 Boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public static ProgramDetailResponse toDto(Program entity) {
        return ProgramDetailResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .discountedPrice(entity.getDiscountedPrice())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deleted(entity.getDeleted())
                .build();
    }

}
