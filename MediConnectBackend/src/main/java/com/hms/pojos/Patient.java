package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
     
	@Column(name="Patient_ID")
	private long PatientID ;
	
	@Column(name="Allergies",length = 50)
	private String allergies ;
	
	@Column(name="Allergies",length = 25)
	private String gender ;
	
	@Column(name="Weight")
	private int weight;
	
	@Column(name="BloodGroup",length = 25)
	private String Bloodgrp;
	
	@Column(name="EmergencyContact",length = 25)
	private String EmergencyContact;
	
	@Column(name="Age")
	private int age ;
	
	
	
	
}
