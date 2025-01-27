package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name= "patient ")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Patient {
     
	@Column(name="patient_ID")
	private long patientID ;
	
	@Column(name="allergies",length = 50)
	private String allergies ;
	
	@Column(name="gender",length = 25)
	private String gender ;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="bloodGroup",length = 25)
	private String Bloodgrp;
	
	@Column(name="emergencyContact",length = 25)
	private String EmergencyContact;
	
	@Column(name="age")
	private int age ;
	
}
	

