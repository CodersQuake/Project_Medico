package com.hms.services;

import com.hms.pojos.MedicalHistory;

public interface MedicalhistoryService {
	
	
	
	String deletehistory(Long historyid) ;

	MedicalHistory addHistory(Long patientId, Long doctorId, Long appointmentId, String diagnosis);
	
	
}
