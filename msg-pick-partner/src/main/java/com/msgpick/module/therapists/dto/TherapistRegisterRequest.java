package com.msgpick.module.therapists.dto;

import com.msgpick.module.shops.domain.Shop;
import com.msgpick.module.therapists.domain.Therapist;
import com.msgpick.msgpick.code.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistRegisterRequest {

    private String name;
    private String position;
    private Nationality nationality;
    private String description;

    public Therapist toEntity(Shop shop) {
        return Therapist.builder()
                .shop(shop)
                .name(name)
                .position(position)
                .nationality(nationality)
                .description(description)
                .build();
    }
}

