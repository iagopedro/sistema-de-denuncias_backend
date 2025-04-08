package org.pdsw.api_pdsw.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cooperative")
public class Cooperative {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String cnpj;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String password;
}
