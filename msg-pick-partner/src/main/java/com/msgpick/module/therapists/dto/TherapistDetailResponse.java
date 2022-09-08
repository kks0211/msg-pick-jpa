package com.msgpick.module.therapists.dto;

import com.msgpick.module.therapists.domain.Therapist;
import com.msgpick.msgpick.code.Nationality;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TherapistDetailResponse {
    private Long id;
    private String name;
    private String position;
    private Nationality nationality;
    private String nationalityDisplayName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    public String getNationalityDisplayName() {
        return this.nationality.getDescription();
    }

    @Builder
    public TherapistDetailResponse(Long id,
                                   String name,
                                   String position,
                                   Nationality nationality,
                                   String description,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt,
                                   Boolean deleted) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.nationality = nationality;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public static TherapistDetailResponse toDto(Therapist entity) {
        return TherapistDetailResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .position(entity.getPosition())
                .nationality(entity.getNationality())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deleted(entity.getDeleted())
                .build();
    }

}
