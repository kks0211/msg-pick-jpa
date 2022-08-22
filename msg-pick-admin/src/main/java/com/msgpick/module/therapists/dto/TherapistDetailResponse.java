package com.msgpick.module.therapists.dto;

import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistDetailResponse extends BaseEntity {
    private Long therapistId;
    private Long shopId;
    private String name;
    private String position;
    private Nationality nationality;
    private String description;
}
