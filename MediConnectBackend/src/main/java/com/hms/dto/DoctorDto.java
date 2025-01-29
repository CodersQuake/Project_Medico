package com.hms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hms.pojos.User_role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {


	@JsonProperty(access = Access.READ_ONLY)
	private String Id;

    @NotBlank(message = "Specialization cannot be blank")
    @Size(min = 5, max = 50, message = "Specialization should be between 5 and 50 characters")
    private String specialization;

    @NotNull(message = "Experience cannot be null")
    @Positive(message = "Experience should be a positive number")
    private Integer experience;

    @NotBlank(message = "Qualification cannot be blank")
    private String qualification;

    @NotNull(message = "Capacity cannot be null")
    @Positive(message = "Capacity should be a positive number")
    private Integer capacity;

    // Extra Columns
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email address")
    private String userEmail;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone_no;
    
	@NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message = "User role is required")
	private User_role user_role;
    
}
