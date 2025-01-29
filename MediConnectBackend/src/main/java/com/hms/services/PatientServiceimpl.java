package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.DoctorDao;
import com.hms.dao.PatientDao;
import com.hms.dto.PatientDto;
import com.hms.exceptions.NoResourceFoundException;
import com.hms.pojos.Doctor;
import com.hms.pojos.Patient;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PatientServiceimpl implements PatientService {
    
	@Autowired
	private PatientDao patientdao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private DoctorDao doctordao;
	
	// register patient
	@Override
	public String registerPatient(PatientDto patient) {

		Patient p = patientdao.save(mapper.map(patient, Patient.class));
		
		return "patient register Succesfully! with id : " + p.getId();
	}

	// view all patient
	@Override
	public List<PatientDto> getallpatients()
	{    
		List<PatientDto> p = patientdao.findAll().stream()
				.map(patient->mapper.map(patient, PatientDto.class))
				.collect(Collectors.toList()) ;
		
		return p;
		
	}

	// delete patient
	@Override
	public String deletePatient(Long patientid)
	{
		if(patientdao.findById(patientid) != null) 
		{ 
			
			patientdao.deleteById(patientid);
				
		}
		else {
			throw new com.hms.exceptions.NoResourceFoundException("No patient is avilable for this id") ;
		}
			
		return "patient record delete Succesfuly!";
		
	}

	// find patient by id
	@Override
	public PatientDto getpatientbyid(Long patientid)
	{
		Patient p = patientdao.findById(patientid)
				.orElseThrow(()-> new com.hms.exceptions.NoResourceFoundException("no patient is avilable for this id !")) ; 
		
		return mapper.map(p, PatientDto.class) ;
	}
	
	
//	//add doctor 
//	@Override
//	public String adddoctortopatient(PatientDto patient ,Long doctid)
//	{   
//		Doctor doct = doctordao.findById(doctid)
//				.orElseThrow(()-> new NoResourceFoundException(" NO doct is avilable to assign")) ;
//		
//		Patient p = patientdao.save(mapper.map(patient, Patient.class)) ;
//		
//        p.setDoctor(doct);
//		
//		return "doctor is assign Succesfully";
//		
//	}

}
