package com.dentalmanagement.service;

import com.dentalmanagement.entity.MedicalHistory;


public interface MedicalhistoryService {
	
	String deletehistory(Long historyid) ;

//	MedicalHistory addHistory(Long patientId, Long doctorId, Long appointmentId, String diagnosis);

	MedicalHistory addHistory(com.dentalmanagement.dto.MedicalhistoryDto historydto);
	
	
}
