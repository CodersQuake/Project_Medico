package com.hms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.AppointmentDao;
import com.hms.dao.DoctorDao;
import com.hms.dao.MedicalHistoryDao;
import com.hms.dao.PatientDao;
import com.hms.dto.MedicalhistoryDto;
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
	private AppointmentDao appoinmentdao;

	@Autowired
	private DoctorDao doctordao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PatientDao patientdao;

	@Autowired
	private MedicalHistoryDao historydao;


	@Override
	public MedicalHistory addHistory(MedicalhistoryDto historydto) {
	    // Fetch the Patient, Doctor, and Appointment entities using their IDs
	    Patient patient = patientdao.findById(historydto.getPatient())
	            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + historydto.getPatient()));

	    Doctor doctor = doctordao.findById(historydto.getDoctor())
	            .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + historydto.getDoctor()));

	    Appointment app = appoinmentdao.findById(historydto.getAppointmentId())
	            .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + historydto.getAppointmentId()));

	    // Create and populate the MedicalHistory entity
	    MedicalHistory medicalHistory = new MedicalHistory();
	    medicalHistory.setPatient(patient);       // Set the Patient entity
	    medicalHistory.setDoctor(doctor);         // Set the Doctor entity
	    medicalHistory.setAppointmentId(app);     // Set the Appointment entity
	    medicalHistory.setDiagnosis(historydto.getDiagnosis());  // Set the diagnosis

	    // Save and return the MedicalHistory entity
	    return historydao.save(medicalHistory);
	}

	@Override
	public String deletehistory(Long historyid) {
		MedicalHistory medicalHistory = historydao.findById(historyid)
				.orElseThrow(() -> new NoResourceFoundException("Medical history not found with ID: " + historyid));

		historydao.delete(medicalHistory);

		return "Medical history deleted successfully with ID: " + historyid;
	}

}
