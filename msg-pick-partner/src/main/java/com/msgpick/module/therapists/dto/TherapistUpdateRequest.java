package com.msgpick.module.therapists.dto;

import com.msgpick.module.therapists.domain.Therapist;
import com.msgpick.msgpick.code.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistUpdateRequest {

    private String name;
    private String position;
    private Nationality nationality;
    private String description;

    public Therapist toEntity() {
        return Therapist.builder()
                .name(name)
                .position(position)
                .nationality(nationality)
                .description(description)
                .build();
    }


}

