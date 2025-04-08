package org.pdsw.api_pdsw.repositories;

import org.pdsw.api_pdsw.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>{ }