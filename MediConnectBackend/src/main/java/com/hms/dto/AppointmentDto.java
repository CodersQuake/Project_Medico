package com.hms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private String payment_status;
    private String medical_problem;
    private String appointment_date;
}
