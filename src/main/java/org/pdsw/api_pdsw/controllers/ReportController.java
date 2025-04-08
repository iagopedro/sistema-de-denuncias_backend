package org.pdsw.api_pdsw.controllers;

import java.util.List;

import org.pdsw.api_pdsw.entities.Report;
import org.pdsw.api_pdsw.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

  private ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping
  public ResponseEntity<List<Report>> getAllReports() {
      List<Report> reports = reportService.getAllReports();
      return ResponseEntity.ok(reports);
  }

  // Add more endpoints for creating, updating, and deleting reports as needed
  // For example:
  @PostMapping
  public ResponseEntity<Report> createReport(@RequestBody Report report) {
      Report createdReport = reportService.createReport(report);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
  }
}
