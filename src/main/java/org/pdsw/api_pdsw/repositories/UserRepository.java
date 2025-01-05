package org.pdsw.api_pdsw.repositories;

import org.pdsw.api_pdsw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> save(Optional<User> user);
}
