package com.msgpick.module.therapists.domain;

import com.msgpick.msgpick.code.Nationality;
import com.msgpick.msgpick.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "therapists")
@NoArgsConstructor
@Entity
public class Therapist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long therapistId;
    private Long shopId;
    private String name;
    private String position;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    private String description;

}
