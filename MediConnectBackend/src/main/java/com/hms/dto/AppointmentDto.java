package com.hms.dto;

import java.time.LocalDateTime;

import com.hms.pojos.Payment_status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {
	
    private Long appointmentId;
    
    private Long doctorId;
    
    private Long patientId;
    
    private Payment_status paymentStatus;
    
    private String medicalProblem;
    
    private LocalDateTime appointmentDate;
}
