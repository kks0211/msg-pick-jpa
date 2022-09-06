package com.msgpick.module.programs.dto;

import com.msgpick.module.programs.domain.Program;
import lombok.*;

@Data
public class ProgramUpdateRequest {
    private Long programId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;

    public Program toEntity() {
        return Program.builder()
                .name(name)
                .price(price)
                .discountedPrice(discountedPrice)
                .description(description)
                .build();
    }

}
