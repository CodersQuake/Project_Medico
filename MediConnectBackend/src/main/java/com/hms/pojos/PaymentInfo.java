package com.hms.pojos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "PaymentInfo")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor

public class PaymentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	@NotNull
	private Long transactionId;
	
	@NotNull
	private Long appointmentId;
	
	@NotNull   
	private String doctorName;
	
	@NotNull  
	private String patientName;
	
	@NotNull  
	private double paymentTotal;
	
	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
//	@JsonFormat(pattern = "yyyy-mm-dd", timezone = "UTC")
	private LocalDateTime paymentDateTime;
	
//	@NotNull
//	@Temporal(value = TemporalType.TIME)
//	private LocalDateTime paymentTime;
	
}
