package com.kaopiz.smsrd.repository;

import com.kaopiz.smsrd.model.ConstructionSite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite, Long> {
    @Query("""
        SELECT cs FROM ConstructionSite cs
        WHERE (:keyword IS NULL OR :keyword = '' 
               OR LOWER(cs.constructionName) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(cs.constructionCode) LIKE LOWER(CONCAT('%', :keyword, '%')))
        ORDER BY cs.constructionName ASC
    """)
    List<ConstructionSite> searchSites(@Param("keyword") String keyword, Pageable pageable);
}
