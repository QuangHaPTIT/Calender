package com.kaopiz.smsrd.repository;

import com.kaopiz.smsrd.model.ScheduleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, Long> {


    @Query("""
            SELECT c FROM ScheduleCategory c
                    WHERE c.id = :id
                      AND c.enterpriseId = :enterpriseId
                      AND c.isActive = true
            """)
    Optional<ScheduleCategory> findByAndEnterpriseId(
            @Param("id") Long id,
            @Param("enterpriseId") Long enterpriseId
    );
}
