package com.hms.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;
import com.hms.pojos.MedicineRecord;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PrescriptionDto {
	

	    @JsonProperty(access = Access.READ_ONLY) 
	    private Long prescriptionId;

	    @NotNull(message = "Doctor ID is required")
	    private Long doctorId;

	    @NotNull(message = "Appointment ID is required")
	    private Long appointmentId;

	    @NotNull(message = "Medicines list cannot be null")
	    private Set<MedicineRecord> medicines; // List of Medicine IDs

	    @NotNull(message = "Prescription description cannot be blank")
	    private String prescriptionDesc; 

	    @NotNull(message = "Quantity is required")
	    @Positive(message = "Quantity must be a positive number")
	    private int quantity;
	    
}
