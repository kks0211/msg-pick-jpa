package com.msgpick.module.shops.dto;

import com.msgpick.msgpick.code.Status;
import com.msgpick.msgpick.code.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UnConfirmedShopsResponse {

    private Long partnerId;
    private String phone;
    private Long shopId;
    private String name;
    private Type type;
    private Status status;
    private String businessArea;
    private String homeCareArea;
    private LocalDateTime createdAt;

}
