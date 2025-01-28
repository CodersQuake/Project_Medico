package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.MedicineRecordDto;
import com.hms.services.MedicineService;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	
    @GetMapping
    public ResponseEntity<?> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicineRecords());
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<?> getMedicineById(@PathVariable Long medicineId) {
        return ResponseEntity.ok(medicineService.getMedicineById(medicineId));
    }

    @PostMapping
    public ResponseEntity<?> createMedicine(@RequestBody MedicineRecordDto medicineRecordDTO) {
        return ResponseEntity.ok(medicineService.createMedicine(medicineRecordDTO));
    }

    @PostMapping("/{medicineId}")
    public ResponseEntity<?> updateMedicine(
            @PathVariable Long medicineId,
            @RequestBody MedicineRecordDto medicineRecordDTO) {
        return ResponseEntity.ok(medicineService.updateMedicine(medicineId, medicineRecordDTO));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok("Medicine deleted successfully.");
    }
	
}
