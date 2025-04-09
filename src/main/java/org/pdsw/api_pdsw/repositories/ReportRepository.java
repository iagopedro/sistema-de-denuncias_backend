package org.pdsw.api_pdsw.repositories;

import java.util.List;

import org.pdsw.api_pdsw.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>{
  List<Report> findByUserId(Long userId);
  List<Report> findByCooperativeId(Long cooperativeId);
}