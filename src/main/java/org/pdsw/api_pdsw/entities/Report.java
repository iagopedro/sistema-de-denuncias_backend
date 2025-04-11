package org.pdsw.api_pdsw.entities;

import java.time.LocalDate;

import org.pdsw.api_pdsw.entities.enums.Category;
import org.pdsw.api_pdsw.entities.enums.ReportStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_report")
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private ReportStatus status;

  @Column(name = "created_at")
  private LocalDate createdAt;

  private LocalDate scheduledAt;

  private LocalDate collectedAt;

  @Column(nullable = false)
  private Category type;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String neighborhood;

  @Column(nullable = false)
  private String latitude;

  @Column(nullable = false)
  private String longitude;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @OneToOne
  @JoinColumn(name = "cooperative_id", nullable = true)
  private Cooperative cooperative;

}
