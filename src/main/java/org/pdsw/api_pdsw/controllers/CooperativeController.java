package org.pdsw.api_pdsw.controllers;

import java.util.List;

import org.pdsw.api_pdsw.entities.Cooperative;
import org.pdsw.api_pdsw.services.CooperativeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cooperatives")
public class CooperativeController {
    
    private final CooperativeService cooperativeService;

    public CooperativeController(CooperativeService cooperativeService) {
      this.cooperativeService = cooperativeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Cooperative>> getAllCooperatives() {
      List<Cooperative> cooperatives = cooperativeService.getAllCooperatives();
      return ResponseEntity.ok(cooperatives);
    }
    
    @PostMapping
    public ResponseEntity<Cooperative> createCooperative(@RequestBody Cooperative cooperative) {
      Cooperative createdCooperative = cooperativeService.createCooperative(cooperative);  
      return ResponseEntity.status(HttpStatus.CREATED).body(createdCooperative);
    }
}
