package org.pdsw.api_pdsw.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.pdsw.api_pdsw.dto.UserResponseDTO;
import org.pdsw.api_pdsw.entities.Report;
import org.pdsw.api_pdsw.entities.User;
import org.pdsw.api_pdsw.entities.enums.ReportStatus;
import org.pdsw.api_pdsw.repositories.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private final ReportRepository reportRepository;
  private final UserService userService;
  private final CooperativeService cooperativeService;

  public ReportService(ReportRepository reportRepository, UserService userService, CooperativeService cooperativeService) {
    this.reportRepository = reportRepository;
    this.userService = userService;
    this.cooperativeService = cooperativeService;
  }

  public List<Report> getAllReports() {
    return reportRepository.findAll();
  }

  public Report createReport(Report report, Long userId, Optional<Long> cooperativeId) {
    if (report.getType() == null) {
      throw new IllegalArgumentException("Report type cannot be null");
    }

    if (report.getCreatedAt() == null) {
      report.setCreatedAt(LocalDate.now());
    }

    if (report.getStatus() == null) {
      report.setStatus(ReportStatus.PENDING);
    }

    UserResponseDTO savedUser = userService.getUserById(userId);

    User user = new User();
    user.setId(userId);
    user.setName(savedUser.getName());
    user.setEmail(savedUser.getEmail());
    user.setPassword(savedUser.getPassword());

    report.setUser(user);

    if (cooperativeId.isPresent()) {
      report.setCooperative(cooperativeService.getCooperativeById(cooperativeId.get()).orElse(null));
    } else {
      report.setCooperative(null);
    }

    return reportRepository.save(report);

  }

  public Report getReportById(Long id) {
    return reportRepository.findById(id).orElse(null);
  }

  public List<Report> getReportsByUserId(Long userId) {
      return reportRepository.findByUserId(userId);
  }

  public Report scheduleReport(Long id, Long cooperativeId) {
    Report report = reportRepository.findById(id).orElse(null);
    if (report != null) {
      report.setCooperative(cooperativeService.getCooperativeById(cooperativeId).orElse(null));
      report.setScheduledAt(LocalDate.now());
      report.setStatus(ReportStatus.SCHEDULED);
      return reportRepository.save(report);
    }
    return null;
  }
  public List<Report> getReportsByCooperativeId(Long cooperativeId) {
    return reportRepository.findByCooperativeId(cooperativeId);
  }

  public Report collectReport(Long id) {
    Report report = reportRepository.findById(id).orElse(null);
    if (report != null && report.getStatus() == ReportStatus.SCHEDULED) {
      report.setCollectedAt(LocalDate.now());
      report.setStatus(ReportStatus.COLLECTED);
      return reportRepository.save(report);
    }
    return null;
  }
}
