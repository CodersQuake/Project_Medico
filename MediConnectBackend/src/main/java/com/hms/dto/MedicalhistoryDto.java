package com.hms.dto;

import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;
import com.hms.pojos.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalhistoryDto {
	

	 private Long patient; 

	 private Long doctor; 
	
	 private Long appointmentId;
	 
	 private String diagnosis;

}
