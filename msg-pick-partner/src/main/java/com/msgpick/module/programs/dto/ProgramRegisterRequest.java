package com.msgpick.module.programs.dto;

import com.msgpick.module.programs.domain.Program;
import com.msgpick.module.shops.domain.Shop;
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

    public Program toEntity(Shop shop) {
        return Program.builder()
                .shop(shop)
                .name(name)
                .price(price)
                .discountedPrice(discountedPrice)
                .description(description)
                .build();
    }
}
