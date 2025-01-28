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
	

	 private Patient patient; 

	 private Doctor doctor; 
	
	 private Appointment appointmentId;
	 
	 private String diagnosis;

}
