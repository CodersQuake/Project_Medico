package com.hms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.MedicineRecordDto;
import com.hms.pojos.MedicineRecord;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface MedicineService {

	List<MedicineRecordDto> getAllMedicineRecords();

    MedicineRecordDto getMedicineById(Long medicineId);

    MedicineRecordDto createMedicine(MedicineRecordDto medicineRecordDTO);

    MedicineRecordDto updateMedicine(Long medicineId, MedicineRecordDto medicineRecordDTO);

    void deleteMedicine(Long medicineId);
    
    
    
	
}
