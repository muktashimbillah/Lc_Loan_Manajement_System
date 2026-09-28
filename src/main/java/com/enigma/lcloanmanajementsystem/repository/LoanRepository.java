package com.enigma.lcloanmanajementsystem.repository;

import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanRepository extends JpaRepository<LoanEntity, Long>, JpaSpecificationExecutor<LoanEntity> {
}
