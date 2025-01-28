package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

	
	
}
