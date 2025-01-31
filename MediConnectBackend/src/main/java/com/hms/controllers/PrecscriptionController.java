package com.hms.controllers;

import com.hms.dto.PrescriptionDto;
import com.hms.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions") 
public class PrecscriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

   
    @PostMapping("/add")
    public ResponseEntity<String> addPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        try {
        	System.out.println(prescriptionDto);
            String responseMessage = prescriptionService.addPrescription(prescriptionDto);
            
            // Sending Mail That Appointment Is Created With Doctor...
            
            
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding prescription: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable("id") Long prescriptionId) {
        try {
            String responseMessage = prescriptionService.deletePrescription(prescriptionId);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting prescription: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/get/{appointmentNo}")
//    public ResponseEntity<PrescriptionDto> getPrescriptionByAppointmentNo(@PathVariable("appointmentNo") Long appointmentNo) {
//        try {
////            PrescriptionDto prescriptionDto = prescriptionService.getpriscriptionbyappno(appointmentNo);
//            return new ResponseEntity<>(prescriptionDto, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
}