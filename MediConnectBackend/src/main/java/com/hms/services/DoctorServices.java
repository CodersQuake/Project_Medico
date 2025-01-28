package com.hms.services;

import java.util.List;

import com.hms.dto.DoctorDto;
import com.hms.exceptions.NoContentException;

public interface DoctorServices {
	
	String addDoctor(DoctorDto doctor);

	List<DoctorDto> getAllDoctors() throws NoContentException;

	String deleteDoctor(Long doctorId) throws NoContentException;

	DoctorDto findDoctorById(Long doctorId);
	
}
