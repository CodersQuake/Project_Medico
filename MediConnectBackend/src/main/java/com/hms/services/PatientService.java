package com.hms.services;

import java.util.List;

import com.hms.dto.PatientDto;


public interface PatientService {
		
	List<PatientDto> getallpatients();
		
	PatientDto getpatientbyid(Long patientid);

	String deletePatient(Long patientid);

	String registerPatient(PatientDto patient);


}

