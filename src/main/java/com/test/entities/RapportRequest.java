package com.test.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportRequest {
    private String rapportType;
    private String startDate;
    private String endDate;

}
