package com.hms.services;

import java.util.List;

import com.hms.dto.AppointmentDto;

public interface AppointmentService {
	
    List<AppointmentDto> getAllAppointments();
    
    AppointmentDto getAppointmentById(Long appointmentId);
    
    AppointmentDto createAppointment(AppointmentDto appointmentDTO);
   
    String deleteAppointment(Long appointmentId);

	AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDTO);

	String assignDoctorToAppointment(Long appointmentId, Long doctorId);
    
}