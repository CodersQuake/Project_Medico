package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.hms.dao.DoctorDao;
import com.hms.dto.DoctorDTO;
import com.hms.exceptions.NoContentException;
import com.hms.pojos.Doctor;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class DoctorServiceImple implements DoctorServices {

	@Autowired
	private DoctorDao doctordao;
	
	@Autowired
	private ModelMapper modelmapper;
	private Doctor doctor;
	
	public DoctorServiceImple() {
		System.out.println("DoctorServiceImple Class : " + getClass());
	}
	
	@Override
	public String addDoctor(DoctorDTO doctor) {
		// TODO Auto-generated method stub
		
		System.out.println("Adding New Doctor...");
		
		Doctor d = doctordao.save(modelmapper.map(doctor, Doctor.class));
		
		System.out.println(d.getId()+" "+d) ;
		
		return "Doctor Added Successfully With ID : " + d.getId();
	}

	@Override
	public List<DoctorDTO> getAllDoctors() throws NoContentException {
		// TODO Auto-generated method stub
		
		List<DoctorDTO> doctors = doctordao.findAll().stream().map(doctor -> modelmapper.map(doctor, DoctorDTO.class)).collect(Collectors.toList());
		if(doctors.size()==0)
		{
			throw new NoContentException("No doctor is in System") ;
		}
		
		return doctors;
	}

	@Override
	public String deleteDoctor(Long doctorId) throws NoContentException {
		// TODO Auto-generated method stub
		
		if(doctordao.findById(doctorId) != null)
		{
			doctordao.deleteById(doctorId);
		}
		else {
			throw new NoContentException("Doctor Is Unavailable with ID : " + doctorId) ;
		}
		
		return "Doctor Removed Successfully!!! With ID : " + doctorId;
	}

	@Override
	public DoctorDTO findDoctorById(Long doctorId) {
		// TODO Auto-generated method stub
		
		Doctor doctor = null;
		try {
			doctor = doctordao.findById(doctorId).orElseThrow(()->new NoContentException("No Doctor is Registered With This ID : " + doctorId));
		} catch (NoContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return modelmapper.map(doctor, DoctorDTO.class);

	}

}

