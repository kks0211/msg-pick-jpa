package com.msgpick.module.programs.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRegisterRequest {
    @Setter
    private Long shopId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;
}
