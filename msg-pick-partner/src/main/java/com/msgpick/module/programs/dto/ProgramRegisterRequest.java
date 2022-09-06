package com.msgpick.module.programs.dto;

import com.msgpick.module.programs.domain.Program;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRegisterRequest {

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
