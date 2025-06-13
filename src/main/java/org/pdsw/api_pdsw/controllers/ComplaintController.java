package org.pdsw.api_pdsw.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.pdsw.api_pdsw.dto.ComplaintRequestDTO;
import org.pdsw.api_pdsw.dto.ComplaintResponseDTO;
import org.pdsw.api_pdsw.entities.Complaint;
import org.pdsw.api_pdsw.services.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }
    
    private ComplaintResponseDTO convertToDTO(Complaint complaint) {
        ComplaintResponseDTO dto = new ComplaintResponseDTO();
        dto.setId(complaint.getId());
        dto.setTitle(complaint.getTitle());
        dto.setType(complaint.getType());
        dto.setDescription(complaint.getDescription());
        dto.setDate(complaint.getDate());
        dto.setStatus(complaint.getStatus());
        dto.setUrgency(complaint.getUrgency());
        dto.setLat(complaint.getLat());
        dto.setLng(complaint.getLng());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponseDTO>> getAllComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        List<ComplaintResponseDTO> dtos = complaints.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ComplaintResponseDTO> createComplaint(@RequestBody ComplaintRequestDTO complaint, @RequestParam Long userId) {
        Complaint createdComplaint = complaintService.createComplaint(complaint, userId);
        ComplaintResponseDTO dto = convertToDTO(createdComplaint);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintsByUserId(@PathVariable Long userId) {
        List<Complaint> complaints = complaintService.getComplaintsByUserId(userId);
        List<ComplaintResponseDTO> dtos = complaints.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponseDTO> getComplaintById(@PathVariable Long id) {
        Complaint complaint = complaintService.getComplaintById(id);
        ComplaintResponseDTO dto = convertToDTO(complaint);
        return ResponseEntity.ok(dto);
    }
}