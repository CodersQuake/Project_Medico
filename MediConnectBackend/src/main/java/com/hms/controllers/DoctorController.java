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

import com.hms.dto.DoctorDTO;
import com.hms.pojos.Doctor;
import com.hms.services.DoctorServices;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorServices doctorService;
	private List<Doctor> doctorList = new ArrayList<>();
	
	public DoctorController() {
		System.out.println("In Const Doctor " + getClass());
	}
	
	// GET ALL DOCTORS
	@GetMapping
	public ResponseEntity<?> getAllDoctors() {
		if(doctorList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorDTOs.add(toDTO(doctor));
        }

        return ResponseEntity.ok(doctorDTOs);
	}

	
	
	
	// POST NEW DOCTOR
	@PostMapping
	public ResponseEntity<?> addNewDoctor(@RequestBody DoctorDTO doctorDTO) {
		System.out.println("Adding New Doctor Object in DB " + doctorDTO);
        Doctor newDoctor = toEntity(doctorDTO);
        doctorList.add(newDoctor);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(newDoctor));
	}
	
	
	// DELETE DOCTOR
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<?> deleteDoctorDetails(@PathVariable Long doctorId) {
        System.out.println("Deleting Doctor Details from DB: " + doctorId);

        Doctor doctorToDelete = doctorList.stream()
            .filter(d -> d.getId().equals(doctorId))
            .findFirst()
            .orElse(null);

        if (doctorToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        doctorList.remove(doctorToDelete);
        return ResponseEntity.ok("Doctor deleted successfully");
	}
	
	// UPDATE DOCTOR
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateDoctorDetails(@RequestBody DoctorDTO doctorDTO, @PathVariable Long doctorId) {
		System.out.println("Updating Doctor Details for ID: " + doctorId + ", DTO: " + doctorDTO);

        Doctor existingDoctor = doctorList.stream()
            .filter(d -> d.getId().equals(doctorId))
            .findFirst()
            .orElse(null);

        if (existingDoctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        // Update the entity based on the DTO
        existingDoctor.setSpecialization(doctorDTO.getSpecialization());
        existingDoctor.setExperience(doctorDTO.getExperience());
        existingDoctor.setQualification(doctorDTO.getQualification());
        existingDoctor.setCapacity(doctorDTO.getCapacity());
        existingDoctor.setUserName(doctorDTO.getName());
        existingDoctor.setUserEmail(doctorDTO.getEmail());
        existingDoctor.setPhone_no(doctorDTO.getPhone());

        return ResponseEntity.ok(toDTO(existingDoctor));
	}
	
	// Entity to DTO
    private DoctorDTO toDTO(Doctor doctor) {
        return new DoctorDTO(
                doctor.getSpecialization(),
                doctor.getExperience(),
                doctor.getQualification(),
                doctor.getCapacity(),
                doctor.getUserName(),
                doctor.getUserEmail(),
                doctor.getPhone_no()
            );
    }
    
    // DTO to Entity
    private Doctor toEntity(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setQualification(doctorDTO.getQualification());
        doctor.setCapacity(doctorDTO.getCapacity());
        doctor.setUserName(doctorDTO.getName());
        doctor.setUserEmail(doctorDTO.getEmail());
        doctor.setPhone_no(doctorDTO.getPhone());
        return doctor;
    }

}
