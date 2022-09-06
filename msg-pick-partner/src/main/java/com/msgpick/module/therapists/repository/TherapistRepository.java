package com.msgpick.module.therapists.repository;

import com.msgpick.module.therapists.domain.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    List<Therapist> findByShop_Id(Long shopId);
}
