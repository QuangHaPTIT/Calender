package com.kaopiz.smsrd.repository;

import com.kaopiz.smsrd.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("""
        SELECT s FROM Schedule s
        LEFT JOIN FETCH s.category
        LEFT JOIN FETCH s.constructionSite
        LEFT JOIN FETCH s.creatorWorker
        WHERE s.deletedAt IS NULL
          AND s.constructionSite.id = :siteId
          AND s.startDatetime < :rangeEnd
          AND s.endDatetime >= :rangeStart
        ORDER BY s.startDatetime ASC, s.id ASC
    """)
    List<Schedule> findOverlappingSchedules(
            @Param("siteId") Long siteId,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeEnd") LocalDateTime rangeEnd
            );

    @Query("""
        SELECT s FROM Schedule s
        LEFT JOIN FETCH s.category
        LEFT JOIN FETCH s.constructionSite
        LEFT JOIN FETCH s.creatorWorker
        LEFT JOIN FETCH s.creatorVendor
        WHERE s.id = :id AND s.deletedAt IS NULL
    """)
    Optional<Schedule> findByIdWithDetails(@Param("id") Long id);
}
