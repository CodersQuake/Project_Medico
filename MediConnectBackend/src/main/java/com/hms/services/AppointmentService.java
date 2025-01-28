package com.hms.services;

import java.util.List;

import com.hms.dto.AppointmentDto;

public interface AppointmentService {
	
    List<AppointmentDto> getAllAppointments();
    
    AppointmentDto getAppointmentById(Long appointmentId);
    
    AppointmentDto createAppointment(AppointmentDto appointmentDTO);
    
    AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDTO);
    
    void deleteAppointment(Long appointmentId);
    
}