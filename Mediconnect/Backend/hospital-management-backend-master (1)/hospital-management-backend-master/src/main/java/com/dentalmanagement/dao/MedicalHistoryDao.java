package com.dentalmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmanagement.entity.MedicalHistory;



public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Long> {
	

}
