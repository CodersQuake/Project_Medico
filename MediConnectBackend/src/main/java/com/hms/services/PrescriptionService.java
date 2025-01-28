package com.hms.services;

import com.hms.dto.PrescriptionDto;
import com.hms.exceptions.PrescriptionNotFoundException;

public interface PrescriptionService {
	
	String addPrescription(PrescriptionDto prescription) ;
	

    
	PrescriptionDto getpriscriptionbyappno(Long appoinmentno) throws PrescriptionNotFoundException;

	String deletePrescription(Long prescriptionId) throws PrescriptionNotFoundException;
	
	
}
