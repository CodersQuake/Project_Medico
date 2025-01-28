package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.PatientDto;
import com.hms.services.PatientService;

@RestController
@RequestMapping("/patient")

public class PatientController {
	
	@Autowired
	private PatientService patientservice ;
	
	
	//register
	@PostMapping("/register")
	public ResponseEntity<?> registerpatient(@RequestBody PatientDto patient)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(patientservice.registerPatient(patient)) ;
	}
	
	// view all 
	@GetMapping("/getall")
    public ResponseEntity<?> getallPatient()
    {
	return ResponseEntity.ok(patientservice.getallpatients());
	   
    }
	
	// delete patient
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletepatient(@PathVariable Long patientid)
	{
		return ResponseEntity.ok(patientservice.deletePatient(patientid));
		
	}
	
	// find patient by id
	@GetMapping("/{patientid}/patientbyid")
	public ResponseEntity<?> getpatientbyid(@PathVariable Long patientid){
		return ResponseEntity.ok(patientservice.getpatientbyid(patientid));
		
	}
	
//	//assign doctor 
//	@PostMapping("/{patientid}/assigndoct")
//	public ResponseEntity<?> assigndoctor(@RequestBody PatientDto patient,@PathVariable Long patientid)
//	{
//		return ResponseEntity.status(HttpStatus.CREATED).body(patientservice.adddoctortopatient(patient, patientid));
//		
//	}

}

