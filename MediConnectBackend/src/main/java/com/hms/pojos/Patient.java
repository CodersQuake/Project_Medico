package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name= "patient")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class Patient extends User{
     
        
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
	

