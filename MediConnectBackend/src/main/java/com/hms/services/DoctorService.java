package com.hms.services;

import java.util.List;

import com.hms.dto.DoctorDto;
import com.hms.exceptions.NoContentException;

public interface DoctorService {
	
	DoctorDto addDoctor(DoctorDto doctor);

	List<DoctorDto> getAllDoctors() throws NoContentException;

	String deleteDoctor(Long doctorId) throws NoContentException;

	DoctorDto findDoctorById(Long doctorId);
	
	DoctorDto updateDoctor(DoctorDto doctor, DoctorDto updatedDoctor);
	
}
