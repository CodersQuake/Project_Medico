package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.pojos.MedicalHistory;
import com.hms.services.MedicalhistoryService;

@RestController
@RequestMapping("/history")
public class MedicalhistoryController {
	
	
    @Autowired
    private MedicalhistoryService medicalHistoryService;

   
    @DeleteMapping("/delete/{historyId}")
    public ResponseEntity<String> deleteHistory(@PathVariable("historyId") Long historyId) {
    	
        String result = medicalHistoryService.deletehistory(historyId);
        
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<MedicalHistory> addHistory(@RequestParam Long patientId,
                                                     @RequestParam Long doctorId,
                                                     @RequestParam Long appointmentId,
                                                     @RequestParam String diagnosis) {
       
        MedicalHistory medicalHistory = medicalHistoryService.addHistory(patientId, doctorId, appointmentId, diagnosis);
        
        // Return the created medical history as response
        
        return ResponseEntity.ok(medicalHistory);
    }
}

