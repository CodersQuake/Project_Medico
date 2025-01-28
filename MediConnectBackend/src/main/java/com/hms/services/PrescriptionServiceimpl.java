package com.hms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.PrescriptionDao;
import com.hms.dto.PrescriptionDto;
import com.hms.exceptions.PrescriptionNotFoundException;
import com.hms.pojos.Prescription;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class PrescriptionServiceimpl implements PrescriptionService {
	
	
	@Autowired
	private PrescriptionDao prescriptiondao ;
	
	
	@Autowired
	private ModelMapper mapper ;

	    @Override
	    public String addPrescription(PrescriptionDto prescriptiondto) {
	    // Convert PrescriptionDto to Prescription entity
	    Prescription prescription = mapper.map(prescriptiondto, Prescription.class);
	    
	    // Save the Prescription entity to the database
	    Prescription savedPrescription = prescriptiondao.save(prescription);

	    // Return success message with saved Prescription's ID
	    return "Prescription added successfully with ID: " + savedPrescription.getPrescription_id();
	}
	    @Override
	    public String deletePrescription(Long prescriptionId) throws PrescriptionNotFoundException {
	        // Check if the prescription exists
	        Prescription prescription = (Prescription) prescriptiondao.findById(prescriptionId)
	        		.orElseThrow(()->new PrescriptionNotFoundException("no prescription is found for this id")) ;

	        prescriptiondao.deleteById(prescriptionId);

	        // Return success message
	        return "Prescription with ID " + prescriptionId + " deleted successfully.";
	    }

	



	@Override
	public PrescriptionDto getpriscriptionbyappno(Long appointmentno) throws PrescriptionNotFoundException {
	    // Fetch Prescription using the appointment number
	    Prescription prescription = prescriptiondao.findByAppointmentIdAppointmentNo(appointmentno);
	    
	    // Check if prescription exists
	    if (prescription == null) {
	        throw new PrescriptionNotFoundException("Prescription not found for appointment number: " + appointmentno);
	    }
	    
	    // Use ModelMapper to convert the Prescription entity to PrescriptionDto
	    PrescriptionDto prescriptionDto = mapper.map(prescription, PrescriptionDto.class);
	    
	    // Return the mapped PrescriptionDto
	    return prescriptionDto;
	}

}
