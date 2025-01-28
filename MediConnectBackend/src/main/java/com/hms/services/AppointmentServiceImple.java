package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.AppointmentDao;
import com.hms.dao.DoctorDao;
import com.hms.dto.AppointmentDto;
import com.hms.exceptions.NoResourceFoundException;
import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImple implements AppointmentService {
    
    @Autowired
    private AppointmentDao appointmentDao;
    
    
    @Autowired
    private DoctorDao doctordao ;
    
    @Autowired
    private ModelMapper modelMapper;
    
    // Getting All Employees...
    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentDao.findAll().stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toList());
    }

    // Get Single AppointmentById...
    @Override
    public AppointmentDto getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    // Create Appointment...
    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment savedAppointment = appointmentDao.save(appointment);
        return modelMapper.map(savedAppointment, AppointmentDto.class);
    }

    // Updating An Appointment...(for patient)
    @Override
    public AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDTO) {
        Appointment appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));
     
        appointment.setMedical_problem(appointmentDTO.getMedicalProblem());

        appointment.setAppointment_date(appointmentDTO.getAppointmentDate());
        return modelMapper.map(appointmentDao.save(appointment), AppointmentDto.class);
    }

    // Deleting an Appointment
    @Override

    public String deleteAppointment(Long appointmentId) {
    	if(appointmentDao.findById(appointmentId)!=null) {
          appointmentDao.deleteById(appointmentId);
          return "Delete appoinment succesfully";
    	}
    	
    	else {
    		return "appoinment is not exist no ";
     		
    	}
    	
    }

    
    //confusion part about doc id(for admin)
    
    @Override
    public String assignDoctorToAppointment(Long appointmentId, Long doctorId) {
        // Fetch the appointment by ID
        Appointment app = appointmentDao.findById(appointmentId).orElseThrow(()->new NoResourceFoundException("Appoinment is not valid"));
     

        // Fetch the doctor by ID
        Doctor doct = doctordao.findById(doctorId).orElseThrow(()->new NoResourceFoundException("doctor is not valid"));
       

        // Assign the doctor to the appointment
        
        app.setDoctor(doct);
        appointmentDao.save(app);

        return "Doctor with ID: " + doctorId + " has been assigned to appointment with ID: " + appointmentId;
    }
    
}