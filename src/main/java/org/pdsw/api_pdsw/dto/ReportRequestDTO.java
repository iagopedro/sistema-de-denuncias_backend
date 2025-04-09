package org.pdsw.api_pdsw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequestDTO {
    private String description;
    private String type;
    private String number;
    private String street;
    private String city;
    private String state;
    private String neighborhood;
}
