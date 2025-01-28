package com.hms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.AppointmentDao;
import com.hms.dao.DoctorDao;
import com.hms.dao.MedicalHistoryDao;
import com.hms.dao.PatientDao;
import com.hms.exceptions.NoResourceFoundException;
import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;
import com.hms.pojos.MedicalHistory;
import com.hms.pojos.Patient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicalhistoryServiceimpl implements MedicalhistoryService {
	
	
	@Autowired
	private AppointmentDao appoinmentdao ;
	
	@Autowired
	private DoctorDao doctordao ;
	
	@Autowired
	private ModelMapper mapper ;
	
	@Autowired
	private PatientDao patientdao ;
	

	@Autowired
	private MedicalHistoryDao historydao ;
      
	
	@Override
	 public MedicalHistory addHistory(Long patientId, Long doctorId, Long appointmentId, String diagnosis) {

	        // Fetch the required entities using the IDs
	        Patient patient = patientdao.findById(patientId)
	                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));
	        
	        Doctor doctor = doctordao.findById(doctorId)
	                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));
	        
	        Appointment appointment = appoinmentdao.findById(appointmentId)
	                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

	        // Create a new MedicalHistory object
	        MedicalHistory medicalHistory = new MedicalHistory();
	        medicalHistory.setPatient(patient);
	        medicalHistory.setDoctor(doctor);
	        medicalHistory.setAppointmentId(appointment);
	        medicalHistory.setDiagnosis(diagnosis);

	        // Save the MedicalHistory entity
	        return historydao.save(medicalHistory);
	    }

	@Override
	public String deletehistory(Long historyid) 
	{
		 MedicalHistory medicalHistory = historydao.findById(historyid)
	                .orElseThrow(() -> new NoResourceFoundException("Medical history not found with ID: " + historyid));

	     historydao.delete(medicalHistory);

	    return "Medical history deleted successfully with ID: " + historyid;
	}



}
