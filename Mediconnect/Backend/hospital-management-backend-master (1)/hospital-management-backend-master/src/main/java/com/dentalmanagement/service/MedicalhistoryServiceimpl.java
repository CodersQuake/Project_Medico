package com.dentalmanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmanagement.advice.MyControllerAdvice;
import com.dentalmanagement.dao.AppointmentDao;
import com.dentalmanagement.dao.MedicalHistoryDao;
import com.dentalmanagement.dto.MedicalhistoryDto;
import com.dentalmanagement.entity.Appointment;
import com.dentalmanagement.entity.MedicalHistory;
import com.dentalmanagement.exception.NoResourceFoundException;

import springfox.documentation.swagger2.mappers.ModelMapper;



@Service
@Transactional
public class MedicalhistoryServiceimpl implements MedicalhistoryService{

	@Autowired
	private AppointmentDao appoinmentdao;


	@Autowired
	private ModelMapper mapper;


	@Autowired
	private MedicalHistoryDao historydao;
	
	private MyControllerAdvice advice ;


	@Override
	public MedicalHistory addHistory(MedicalhistoryDto historydto) {
	    // Fetch the Patient, Doctor, and Appointment entities using their IDs
	  
	    Appointment app = appoinmentdao.findById(historydto.getAppointmentId().intValue())
	            .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + historydto.getAppointmentId()));

	    // Create and populate the MedicalHistory entity
	    MedicalHistory medicalHistory = new MedicalHistory();
	       // Set the Doctor entity
	    medicalHistory.setAppointmentId(app);     // Set the Appointment entity
	    medicalHistory.setDiagnosis(historydto.getDiagnosis());  // Set the diagnosis

	    // Save and return the MedicalHistory entity
	    return historydao.save(medicalHistory);
	}

	@Override
	public String deletehistory(Long historyid) {
		MedicalHistory medicalHistory = historydao.findById(historyid)
				.orElseThrow(null);

		historydao.delete(medicalHistory);

		return "Medical history deleted successfully with ID: " + historyid;
	}



}
