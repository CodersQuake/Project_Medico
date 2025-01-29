package com.hms.dto;

import com.hms.pojos.User_role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PatientDto {
    
	// ### USER
	
	@NotNull(message = "User name is required")
    @Size(min = 3, max = 100, message = "User name must be between 3 and 100 characters")
	private String userName;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String userEmail;
	
	@NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message = "Phone number is required")
	private String phone_no;
	
	@NotBlank(message = "User role is required")
	private User_role user_role;
	
	// ---------------------------------------------------
	
	// ### PATIENT
	
	@NotBlank(message = "Allergies is required")
	private String allergies ;
	
	@NotBlank(message = "Gender is required")
	private String gender ;
	
	@Min(value = 1, message = "Weight must be at least 1 kg")
    @Max(value = 500, message = "Weight cannot be more than 500 kg")
	private int weight;
	
	@NotBlank(message = "Blood group is required")
	private String bloodgrp;

	@NotBlank(message = "Emergency contact is required")
	private String emergencyContact;
	
	@Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age cannot be more than 150 years")
	private int age ;
	

}
