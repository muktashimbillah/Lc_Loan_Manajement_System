package com.enigma.lcloanmanajementsystem.repository;

import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long>, JpaSpecificationExecutor<LoanEntity> {
    @Query("SELECT l FROM LoanEntity l WHERE l.id=:id and l.user.id=:userId")
    LoanEntity findByIdAndUserId(Long id, Long userId);

    @Query("SELECT l FROM LoanEntity l WHERE l.user.id=:id")
    List<LoanEntity> findByUserId(Long id);
}
