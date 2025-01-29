package com.hms.controllers;

import java.util.List;

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
	

    @GetMapping("/getallmedicines") // Ensure it's mapped properly
    public ResponseEntity<List<MedicineRecordDto>> getAllMedicines() {
        List<MedicineRecordDto> medicines = medicineService.getAllMedicineRecords();
        return ResponseEntity.ok(medicines);
    }
    
    
    @GetMapping("/getmedicine/{medicineId}")
    public ResponseEntity<?> getMedicineById(@PathVariable Long medicineId) {
        return ResponseEntity.ok(medicineService.getMedicineById(medicineId));
    }
    

    @PostMapping("/addmedicine")
    public ResponseEntity<?> createMedicine(@RequestBody MedicineRecordDto medicineRecordDTO) {
        return ResponseEntity.ok(medicineService.createMedicine(medicineRecordDTO));
    }

    @PostMapping("/updatemedicine/{medicineId}")
    public ResponseEntity<?> updateMedicine(
            @PathVariable Long medicineId,
            @RequestBody MedicineRecordDto medicineRecordDTO) {
        return ResponseEntity.ok(medicineService.updateMedicine(medicineId, medicineRecordDTO));
    }

    
    @DeleteMapping("/delete/{medicineId}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok("Medicine deleted successfully.");
    }
	
}
