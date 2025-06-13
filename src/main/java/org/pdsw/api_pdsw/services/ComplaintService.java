package org.pdsw.api_pdsw.services;

import java.time.LocalDate;
import java.util.List;

import org.pdsw.api_pdsw.dto.ComplaintRequestDTO;
import org.pdsw.api_pdsw.entities.Complaint;
import org.pdsw.api_pdsw.repositories.ComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserService userService;

    public ComplaintService(ComplaintRepository complaintRepository, UserService userService) {
        this.complaintRepository = complaintRepository;
        this.userService = userService;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint createComplaint(ComplaintRequestDTO complaintDTO, Long userId) {
        if (complaintDTO.getTitle() == null || complaintDTO.getType() == null || complaintDTO.getDescription() == null ||
            complaintDTO.getUrgency() == null || complaintDTO.getLat() == null || complaintDTO.getLng() == null) {
            throw new IllegalArgumentException("All fields must be provided");
        }
        
        if (complaintDTO.getDate() == null) {
            complaintDTO.setDate(LocalDate.now().toString());
        }

        Complaint newComplaint = new Complaint();
        newComplaint.setTitle(complaintDTO.getTitle());
        newComplaint.setType(complaintDTO.getType());
        newComplaint.setDescription(complaintDTO.getDescription());
        newComplaint.setDate(complaintDTO.getDate());
        newComplaint.setStatus(complaintDTO.getStatus());
        newComplaint.setUrgency(complaintDTO.getUrgency());
        newComplaint.setLat(complaintDTO.getLat());
        newComplaint.setLng(complaintDTO.getLng());
        newComplaint.setImage(complaintDTO.getImage());
        newComplaint.setUserId(userId);

        return complaintRepository.save(newComplaint);
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    public List<Complaint> getComplaintsByUserId(Long userId) {
        return complaintRepository.findByUserId(userId);
    }
}