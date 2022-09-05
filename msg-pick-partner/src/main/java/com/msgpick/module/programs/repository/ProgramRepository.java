package com.msgpick.module.programs.repository;

import com.msgpick.module.programs.domain.Program;
import com.msgpick.module.programs.dto.ProgramDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByShop_Id(Long shopId);
}
