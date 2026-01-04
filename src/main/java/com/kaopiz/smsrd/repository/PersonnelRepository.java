package com.kaopiz.smsrd.repository;

import com.kaopiz.smsrd.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
