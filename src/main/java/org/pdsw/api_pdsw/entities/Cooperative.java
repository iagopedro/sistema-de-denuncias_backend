package org.pdsw.api_pdsw.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_report")
public class Cooperative {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long id;

  private String name;

  private String cnpj;

  private String email;

  private String phone;
  
  private String password;
}
