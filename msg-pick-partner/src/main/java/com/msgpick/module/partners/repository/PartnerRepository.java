package com.msgpick.module.partners.repository;

import com.msgpick.module.partners.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByPhone(String phone);
}
