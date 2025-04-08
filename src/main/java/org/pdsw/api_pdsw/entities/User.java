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

    private String name;

    private String email;

    private String password;

    private String picture;
    
    private UserType type;

}
