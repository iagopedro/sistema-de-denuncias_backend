package org.pdsw.api_pdsw.repositories;

import java.util.Optional;

import org.pdsw.api_pdsw.entities.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {
  Optional<Cooperative> save(Optional<Cooperative> cooperative);
}