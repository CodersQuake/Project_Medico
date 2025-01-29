package com.hms.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.AppointmentDao;
import com.hms.dao.DoctorDao;
import com.hms.dao.PatientDao;
import com.hms.dto.AppointmentDto;
import com.hms.exceptions.NoResourceFoundException;
import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;
import com.hms.pojos.Patient;
import com.hms.pojos.Payment_status;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImple implements AppointmentService {
    
    @Autowired
    private AppointmentDao appointmentDao;
    
    @Autowired
    private DoctorDao doctorRepository;
    
    @Autowired
    private PatientDao patientRepository;
    
    @Autowired
    private DoctorDao doctordao ;
    
    @Autowired
    private ModelMapper modelMapper;
    
    // Getting All Employees...
    @Override //[done]
    public List<AppointmentDto> getAllAppointments() {
        // Fetch all appointments from the DAO
        List<Appointment> appointments = appointmentDao.findAll();

        // Convert each Appointment entity to AppointmentDto
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDto appointmentDto = new AppointmentDto();

            // Manually map fields
            appointmentDto.setAppointmentId(appointment.getAppointmentId());
            appointmentDto.setDoctorId(appointment.getDoctorId().getId());  // Assuming Doctor has a getDoctorId method
            appointmentDto.setPatientId(appointment.getPatientId().getId());  // Assuming Patient has a getPatientId method
            appointmentDto.setPayment_status(appointment.getPayment_status().toString());  // Convert enum to String
            appointmentDto.setMedical_problem(appointment.getMedical_problem());
            appointmentDto.setAppointment_date(appointment.getAppointment_date().toString());  // Convert LocalDate to String

            // Add to the list of appointment DTOs
            appointmentDtos.add(appointmentDto);
        }

        return appointmentDtos;
    }
    // Get Single AppointmentById...
    @Override  //done
    public AppointmentDto getAppointmentById(Long appointmentId) {
        // Fetch the appointment by ID from the DAO
        Appointment appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));

        // Manually map the Appointment entity to AppointmentDto
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setDoctorId(appointment.getDoctorId().getId());  // Assuming Doctor has a getDoctorId method
        appointmentDto.setPatientId(appointment.getPatientId().getId());  // Assuming Patient has a getPatientId method
        appointmentDto.setPayment_status(appointment.getPayment_status().toString());  // Convert enum to String
        appointmentDto.setMedical_problem(appointment.getMedical_problem());
        appointmentDto.setAppointment_date(appointment.getAppointment_date().toString());  // Convert LocalDate to String

        return appointmentDto;
    }


    
    @Override //done
    public AppointmentDto createAppointment(AppointmentDto appointmentDTO) {
        // Fetch the doctor and patient using their IDs
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Check if appointmentDate is null or empty
        if (appointmentDTO.getAppointment_date() == null || appointmentDTO.getAppointment_date().isEmpty()) {
            throw new IllegalArgumentException("Appointment date cannot be null or empty");
        }

        // Parse the appointment date
        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(appointmentDTO.getAppointment_date()); // Assumes the date is in the ISO 8601 format
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use the correct ISO 8601 format.", e);
        }

        // Create a new Appointment entity and manually map fields
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);  // Set doctor to the entity
        appointment.setPatient(patient);  // Set patient to the entity
        appointment.setAppointment_date(appointmentDate);  // Set parsed date
        appointment.setMedical_problem(appointmentDTO.getMedical_problem());
        
        // Set payment status based on DTO value
        Payment_status status = Payment_status.valueOf(appointmentDTO.getPayment_status());
        appointment.setPayment_status(status);

        // Save and return the saved appointment
        Appointment savedAppointment = appointmentDao.save(appointment);

        // Manually map the fields from savedAppointment to AppointmentDto
        AppointmentDto savedAppointmentDTO = new AppointmentDto();
        savedAppointmentDTO.setAppointmentId(savedAppointment.getAppointmentId());
        savedAppointmentDTO.setAppointment_date(savedAppointment.getAppointment_date().toString()); // Convert LocalDate to String
        savedAppointmentDTO.setMedical_problem(savedAppointment.getMedical_problem());
        savedAppointmentDTO.setPayment_status(savedAppointment.getPayment_status().toString()); 

        return savedAppointmentDTO;
    }


    @Override  //done
    public AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDTO) {
        // Fetch the existing appointment from the DAO by ID
        Appointment appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));

        // Update the medical problem
        appointment.setMedical_problem(appointmentDTO.getMedical_problem());

        // Parse the appointment date from the DTO and set it
        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(appointmentDTO.getAppointment_date()); // Assumes the date is in the ISO 8601 format
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use the correct ISO 8601 format.", e);
        }
        appointment.setAppointment_date(appointmentDate);

        // Save the updated appointment and manually map the fields to the AppointmentDto
        Appointment savedAppointment = appointmentDao.save(appointment);

        // Manually map the saved appointment to AppointmentDto
        AppointmentDto updatedAppointmentDto = new AppointmentDto();
     
      
        updatedAppointmentDto.setMedical_problem(savedAppointment.getMedical_problem());
        updatedAppointmentDto.setAppointment_date(savedAppointment.getAppointment_date().toString());  // Convert LocalDate to String

        return updatedAppointmentDto;
    }


    // Deleting an Appointment
    @Override
                 //done
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
    
    @Override //done
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