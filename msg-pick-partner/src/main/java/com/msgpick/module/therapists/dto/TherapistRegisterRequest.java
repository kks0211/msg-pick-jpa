package com.msgpick.module.therapists.dto;

import com.msgpick.msgpick.code.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistRegisterRequest {
    private Long therapistId;
    @Setter
    private Long shopId;
    private String name;
    private String position;
    private Nationality nationality;
    private String description;
}

