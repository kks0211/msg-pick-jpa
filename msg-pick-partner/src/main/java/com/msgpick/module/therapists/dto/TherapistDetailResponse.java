package com.msgpick.module.therapists.dto;

import com.msgpick.module.therapists.domain.Therapist;
import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Getter
@NoArgsConstructor
public class TherapistDetailResponse extends BaseEntity {
    private Long id;
    private String name;
    private String position;
    private Nationality nationality;
    private String nationalityDisplayName;
    private String description;

    public String getNationalityDisplayName() {
        return this.nationality.getDescription();
    }

    @Builder
    public TherapistDetailResponse(Long id, String name, String position, Nationality nationality, String description) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.nationality = nationality;
        this.description = description;
    }

    public static TherapistDetailResponse toDto(Therapist entity) {
        return TherapistDetailResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .position(entity.getPosition())
                .nationality(entity.getNationality())
                .description(entity.getDescription())
                .build();
    }


}
