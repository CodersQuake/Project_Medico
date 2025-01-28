package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.MedicineRecord;

public interface MedicineDao extends JpaRepository<MedicineRecord, Long>{

}
