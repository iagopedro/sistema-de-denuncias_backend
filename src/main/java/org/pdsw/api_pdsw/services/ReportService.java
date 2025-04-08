package org.pdsw.api_pdsw.services;

import java.util.List;

import org.pdsw.api_pdsw.entities.Report;
import org.pdsw.api_pdsw.repositories.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private final ReportRepository reportRepository;

  public ReportService(ReportRepository reportRepository) {
      this.reportRepository = reportRepository;
  }

  public List<Report> getAllReports() {
      return reportRepository.findAll();
  }

  public Report createReport(Report report) {
      return reportRepository.save(report);
  }

  public Report getReportById(Long id) {
      return reportRepository.findById(id).orElse(null);
  }
}
