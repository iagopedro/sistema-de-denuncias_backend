package org.pdsw.api_pdsw.entities;

import org.pdsw.api_pdsw.entities.enums.UserType;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String picture;
    
    @Column(nullable = false)
    private UserType type;

}
