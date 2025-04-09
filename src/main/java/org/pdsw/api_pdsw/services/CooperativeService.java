package org.pdsw.api_pdsw.services;

import java.util.List;
import java.util.Optional;

import org.pdsw.api_pdsw.entities.Cooperative;
import org.pdsw.api_pdsw.repositories.CooperativeRepository;
import org.springframework.stereotype.Service;

@Service
public class CooperativeService {

    private final CooperativeRepository cooperativeRepository;
    private final PasswordService passwordService;

    public CooperativeService(CooperativeRepository cooperativeRepository, PasswordService passwordService) {
        this.cooperativeRepository = cooperativeRepository;
        this.passwordService = passwordService;
    }
    
    public List<Cooperative> getAllCooperatives() {
        return cooperativeRepository.findAll();
    }

    public Cooperative createCooperative(Cooperative cooperative) {
        var hashedPassword = passwordService.hashPassword(cooperative.getPassword());
        cooperative.setPassword(hashedPassword);
        return this.cooperativeRepository.save(cooperative);
    }

    public Optional<Cooperative> getCooperativeById(Long id) {
        Optional<Cooperative> cooperative = cooperativeRepository.findById(id);
        return cooperative;
    }
}
