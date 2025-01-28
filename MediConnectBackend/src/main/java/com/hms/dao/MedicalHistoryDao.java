package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.MedicalHistory;


public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Long> {
	

}
