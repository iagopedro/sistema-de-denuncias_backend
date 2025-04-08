package org.pdsw.api_pdsw.entities;

import java.time.LocalDate;

import org.pdsw.api_pdsw.entities.enums.Category;
import org.pdsw.api_pdsw.entities.enums.ReportStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_report")
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private ReportStatus status;

  private LocalDate createdAt;

  private Category type;
  
  private User user;
  
  private Location location;
  
  private Cooperative cooperative;

}
