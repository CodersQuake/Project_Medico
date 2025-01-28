package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.Prescription;

public interface PrescriptionDao extends JpaRepository<Prescription, Long>{
	
	// Custom query to find Prescription by appointmentId's appointmentNo
//    Prescription findByAppointmentIdAppointmentNo(Long appointmentNo);
 
}
