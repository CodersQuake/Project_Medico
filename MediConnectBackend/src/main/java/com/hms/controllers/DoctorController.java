package com.hms.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.hms.dto.DoctorDto;
import com.hms.exceptions.NoContentException;
import com.hms.pojos.Doctor;
import com.hms.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
//	private List<Doctor> doctorList = new ArrayList<>();

	
	public DoctorController() {
		System.out.println("In Const Doctor " + getClass());
	}
	
	// GET ALL DOCTORS
	@GetMapping
	public ResponseEntity<List<DoctorDto>> getAllDoctors() throws NoContentException {
		
		// TEST [DONE]
		
	    List<DoctorDto> doctorDTO_List = doctorService.getAllDoctors();
	    
	    if (doctorDTO_List.isEmpty()) {
	        return ResponseEntity.noContent().build(); // Returns 204 No Content
	    }

	    return ResponseEntity.ok(doctorDTO_List); // Returns 200 OK with the list
	}


	// FIND DOCTOR BY ID
	@GetMapping("/search/{doctorId}")
	public ResponseEntity<?> findDoctor(@PathVariable Long doctorId) {
		// TEST [DONE]
		DoctorDto doctorDTO = doctorService.findDoctorById(doctorId);
		return ResponseEntity.ok(doctorDTO);
	}
	
	
	
	// POST NEW DOCTOR
	@PostMapping("/add")
	public ResponseEntity<?> addNewDoctor(@RequestBody DoctorDto doctorDTO) {
		// TEST [DONE]
		System.out.println("Adding New Doctor Object in DB " + doctorDTO);
        Doctor newDoctor = toEntity(doctorDTO);
//        doctorList.add(newDoctor);
        DoctorDto addedDoctor = doctorService.addDoctor(doctorDTO);
        if(addedDoctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(addedDoctor);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDoctor);
	}
	
	
	// DELETE DOCTOR
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<?> deleteDoctorDetails(@PathVariable Long doctorId) throws NoContentException {
		// TEST [DONE]
        System.out.println("Deleting Doctor Details from DB: " + doctorId);

//        Doctor doctorToDelete = doctorList.stream()
//            .filter(d -> d.getId().equals(doctorId))
//            .findFirst()
//            .orElse(null);
        
        String doctorToDelete = doctorService.deleteDoctor(doctorId);

        if (doctorToDelete == "") {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        
        return ResponseEntity.ok("Doctor deleted successfully");
	}
	
	// UPDATE DOCTOR
	@PutMapping("/update/{doctorId}")
	public ResponseEntity<?> updateDoctorDetails(@RequestBody DoctorDto doctorDTO, @PathVariable Long doctorId) {
		System.out.println("Updating Doctor Details for ID: " + doctorId + ", DTO: " + doctorDTO);

		DoctorDto existingDoctor = doctorService.findDoctorById(doctorId);
        
		doctorService.updateDoctor(existingDoctor, doctorDTO);
		
        return ResponseEntity.ok(existingDoctor);
	}
	
	// Entity to DTO conversion
	private DoctorDto toDTO(Doctor newDoctor) {
	    DoctorDto doctorDto = new DoctorDto();
	    doctorDto.setSpecialization(newDoctor.getSpecialization());
	    doctorDto.setExperience(newDoctor.getExperience());
	    doctorDto.setQualification(newDoctor.getQualification());
	    doctorDto.setCapacity(newDoctor.getCapacity());
	    doctorDto.setUserName(newDoctor.getUserName());
	    doctorDto.setUserEmail(newDoctor.getUserEmail());
	    doctorDto.setPhone_no(newDoctor.getPhone_no());
	    return doctorDto;
	}


    
    // DTO to Entity
    private Doctor toEntity(DoctorDto doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setQualification(doctorDTO.getQualification());
        doctor.setCapacity(doctorDTO.getCapacity());
        doctor.setUserName(doctorDTO.getUserName());
        doctor.setUserEmail(doctorDTO.getUserEmail());
        doctor.setPhone_no(doctorDTO.getPhone_no());
        return doctor;
    }

}
