package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.hms.dao.DoctorDao;
import com.hms.dto.DoctorDto;
import com.hms.exceptions.NoContentException;
import com.hms.pojos.Doctor;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class DoctorServiceImple implements DoctorService {

	@Autowired
	private DoctorDao doctordao;
	
	@Autowired
	private ModelMapper modelMapper;
	private Doctor doctor;
	
	public DoctorServiceImple() {
		System.out.println("DoctorServiceImple Class : " + getClass());
	}
	
	@Override
	public String addDoctor(DoctorDto doctor) {
		// TODO Auto-generated method stub
		
		System.out.println("Adding New Doctor...");
		
		Doctor d = doctordao.save(modelMapper.map(doctor, Doctor.class));
		
		System.out.println(d.getId()+" "+d) ;
		
		return "Doctor Added Successfully With ID : " + d.getId();
	}

	@Override
	public List<DoctorDto> getAllDoctors() throws NoContentException {
		// TODO Auto-generated method stub
		
		List<DoctorDto> doctors = doctordao.findAll().stream().map(doctor -> modelMapper.map(doctor, DoctorDto.class)).collect(Collectors.toList());
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
	public DoctorDto findDoctorById(Long doctorId) {
		// TODO Auto-generated method stub
		
		Doctor doctor = null;
		try {
			doctor = doctordao.findById(doctorId).orElseThrow(()->new NoContentException("No Doctor is Registered With This ID : " + doctorId));
		} catch (NoContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelMapper.map(doctor, DoctorDto.class);

	}

}

