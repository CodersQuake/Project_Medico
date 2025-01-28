package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.Patient;


public interface PatientDao extends JpaRepository<Patient,Long>{

	
}
