package com.hms.services;

import java.util.List;

import com.hms.dto.DoctorDTO;
import com.hms.exceptions.NoContentException;

public interface DoctorServices {
	
	String addDoctor(DoctorDTO doctor);

	List<DoctorDTO> getAllDoctors() throws NoContentException;

	String deleteDoctor(Long doctorId) throws NoContentException;

	DoctorDTO findDoctorById(Long doctorId);
	
}
