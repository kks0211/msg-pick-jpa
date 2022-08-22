package com.msgpick.module.programs.dto;

import lombok.*;

@Data
public class ProgramUpdateRequest {
    private Long programId;
    private Long shopId;
    private String name;
    private Long price;
    private Long discountedPrice;
    private String description;

}
