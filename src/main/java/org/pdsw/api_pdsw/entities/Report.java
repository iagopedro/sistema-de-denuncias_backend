package org.pdsw.api_pdsw.entities;

import java.time.LocalDate;

import org.pdsw.api_pdsw.entities.enums.Category;
import org.pdsw.api_pdsw.entities.enums.ReportStatus;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @Column(nullable = false)
  private Category type;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
 
  @OneToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;
  
  @OneToOne
  @JoinColumn(name = "cooperative_id", nullable = true)
  private Cooperative cooperative;

}
