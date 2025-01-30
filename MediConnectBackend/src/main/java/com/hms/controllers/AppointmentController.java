package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.AppointmentDto;
import com.hms.dto.PatientDto;
import com.hms.services.AppointmentService;
import com.hms.services.EmailSendingService;
import com.hms.services.PatientService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private EmailSendingService emailSendingService;
	
	@Autowired
	private PatientService patientService;
	
	// Retrieving All The Appointments..

    @GetMapping("/getall")
	public ResponseEntity<?> getAllAppointments(){
    	
		return ResponseEntity.ok(appointmentService.getAllAppointments());
		
	}
	
	// Retrieving single Appointment by ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }
    
    // Creating New Appointment...
//    @PostMapping("/book")
//    public ResponseEntity<?>createAppointment(@RequestBody AppointmentDto appointmentDto) {
//        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDto));
//    }

    @PostMapping("/book")
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDTO) {
        // Validate appointment_date to ensure it is not null or empty
        if (appointmentDTO.getAppointment_date() == null || appointmentDTO.getAppointment_date().isEmpty()) {
        	System.out.println("date is emptyy");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDTO);
        
        PatientDto pat = patientService.getpatientbyid(appointmentDTO.getPatientId());
        
        emailSendingService.sendSimpleMessage("mahajanvikrant1704@gmail.com", "Appointment Booking!!!", "Congratulations... VIKRANT MAHAJAN. Your Appointment Has Been Booked With Doctor ID : " + appointmentDTO.getDoctorId());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }
    
   // Updating Appointment with ID...
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<?> updateAppointment( @PathVariable Long appointmentId, @RequestBody AppointmentDto appointmentDTO) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentId, appointmentDTO));
    }
    
    // Deleting The Appointment...
    @DeleteMapping("/delete/{appointmentId}")//[done]
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("Appointment deleted successfully.");
    }
    
    @PutMapping("/{appointmentId}/assignDoctor/{doctorId}")//[done]
    public ResponseEntity<String> assignDoctorToAppointment( @PathVariable Long appointmentId,  @PathVariable Long doctorId) {
      
           String response = appointmentService.assignDoctorToAppointment(appointmentId, doctorId);
            return ResponseEntity.ok(response);
       
    }
    
}
