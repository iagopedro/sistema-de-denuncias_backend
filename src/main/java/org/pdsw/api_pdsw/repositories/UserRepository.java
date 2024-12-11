package org.pdsw.api_pdsw.repositories;

import org.pdsw.api_pdsw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User save(User user);
}
