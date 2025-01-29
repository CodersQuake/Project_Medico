package com.hms.services;

import java.util.List;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.MedicineDao;
import com.hms.dto.MedicineRecordDto;
import com.hms.pojos.MedicineRecord;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MedicineServiceImple implements MedicineService {
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<MedicineRecordDto> getAllMedicineRecords() {
		// TODO Auto-generated method stub
		
		List<MedicineRecordDto> medicineList = medicineDao.findAll().stream().map(medicine -> modelMapper.map(medicine, MedicineRecordDto.class)).collect(Collectors.toList());
		
		return medicineList;
	}
	
	
    @Override
    public MedicineRecordDto getMedicineById(Long medicineId) {
        MedicineRecord medicine = medicineDao.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + medicineId));
        return new MedicineRecordDto(medicine.getMedicineId(), medicine.getPrice(), medicine.getName());
    }

    @Override
    public MedicineRecordDto createMedicine(MedicineRecordDto medicineRecordDTO) {
    	
        MedicineRecord medicine = new MedicineRecord();
        medicine.setPrice(medicineRecordDTO.getMedicinePrice());
        medicine.setName(medicineRecordDTO.getMedicineName());
        
        MedicineRecord savedMedicine = medicineDao.save(medicine);
        return new MedicineRecordDto(savedMedicine.getMedicineId(), savedMedicine.getPrice(), savedMedicine.getName());
    }


	@Override
	public MedicineRecordDto updateMedicine(Long medicineId, MedicineRecordDto medicineRecordDTO) {
	    MedicineRecord medicine = medicineDao.findById(medicineId)
	            .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + medicineId));
	    
	    medicine.setPrice(medicineRecordDTO.getMedicinePrice());
	    medicine.setName(medicineRecordDTO.getMedicineName());
	    MedicineRecord updatedMedicine = medicineDao.save(medicine);
	    
	    return new MedicineRecordDto(updatedMedicine.getMedicineId(), updatedMedicine.getPrice(), updatedMedicine.getName());
	}

    @Override
    public void deleteMedicine(Long medicineId) {
        if (!medicineDao.existsById(medicineId)) {
            throw new RuntimeException("Medicine not found with ID: " + medicineId);
        }
        medicineDao.deleteById(medicineId);
        System.out.println("Medicine is Removed Successfully!!!");
    }

}
