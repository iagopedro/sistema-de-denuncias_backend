package org.pdsw.api_pdsw.repositories;

import java.util.List;

import org.pdsw.api_pdsw.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserId(Long userId);
}