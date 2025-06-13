package org.pdsw.api_pdsw.dto;

import lombok.Data;

@Data
public class ComplaintRequestDTO {
    private String title;
    private String type;
    private String description;
    private String date;
    private String status;
    private String urgency;
    private String lat;
    private String lng;
    private String image;
    private Long userId;
}