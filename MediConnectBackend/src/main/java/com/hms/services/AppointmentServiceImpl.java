package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.AppointmentDao;
import com.hms.dto.AppointmentDto;
import com.hms.pojos.Appointment;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    
    @Autowired
    private AppointmentDao appointmentDao;
    
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

    // Updating An Appointment...
    @Override
    public AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDTO) {
        Appointment appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));
        appointment.setDoctorId(modelMapper.map(appointmentDTO.getDoctorId(), Appointment.class).getDoctorId());
        appointment.setPatientId(modelMapper.map(appointmentDTO.getPatientId(), Appointment.class).getPatientId());
        appointment.setMedical_problem(appointmentDTO.getMedicalProblem());
        appointment.setPayment_status(appointmentDTO.getPaymentStatus());
        appointment.setAppointment_date(appointmentDTO.getAppointmentDate());
        return modelMapper.map(appointmentDao.save(appointment), AppointmentDto.class);
    }

    // Deleting an Appointment
    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentDao.deleteById(appointmentId);
    }

}