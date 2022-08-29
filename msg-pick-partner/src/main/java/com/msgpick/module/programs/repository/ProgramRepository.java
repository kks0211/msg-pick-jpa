package com.msgpick.module.programs.repository;

import com.msgpick.module.programs.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
