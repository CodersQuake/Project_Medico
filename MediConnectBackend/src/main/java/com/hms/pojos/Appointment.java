package com.hms.pojos;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctorId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patientId;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "paymentStatus")
	private PaymentInfo payment_status; //do emum here pending , completed,fialed
	
	@NotNull
	private String medical_problem;
	
	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
	private LocalDateTime appointment_date;
	
}
