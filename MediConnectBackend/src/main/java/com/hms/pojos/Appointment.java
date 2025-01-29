package com.hms.pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	
//	@NotNull
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctorId;
	
//	@NotNull
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patientId;
	
//	@NotNull
	@Enumerated(EnumType.STRING)
	private Payment_status payment_status; 
	
//	@NotNull
	private String medical_problem;
	
//	@NotNull
//	@Temporal(value = TemporalType.TIMESTAMP)
	private LocalDate appointment_date;

	public void setDoctor(Doctor doc) {
		// TODO Auto-generated method stub
		this.doctorId = doc;
		//here some confusion regarding doctid type ,expected: long docid

	}

	public void setPatient(Patient patient) {
		this.patientId=patient;
		
	}
	
	
	//make appoinment method 
	
}
