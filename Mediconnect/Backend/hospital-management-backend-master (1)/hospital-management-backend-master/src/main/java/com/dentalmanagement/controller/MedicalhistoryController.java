package com.dentalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dentalmanagement.dto.MedicalhistoryDto;
import com.dentalmanagement.entity.MedicalHistory;
import com.dentalmanagement.service.MedicalhistoryService;

@RestController
@RequestMapping("/history")
public class MedicalhistoryController {
	
	
   @Autowired
   private MedicalhistoryService medicalHistoryService;

   
    @DeleteMapping("/delete/{historyId}")
    public ResponseEntity<String> deleteHistory(@PathVariable("historyId") Long historyId) {
    	
        String result = medicalHistoryService.deletehistory(historyId) ;
       
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<MedicalHistory> addHistory(@RequestBody MedicalhistoryDto historydto) {
       
        MedicalHistory medicalHistory = medicalHistoryService.addHistory( historydto);
        
        // Return the created medical history as response
        
        return ResponseEntity.ok(medicalHistory);
    }
}

