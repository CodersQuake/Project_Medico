package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Prescription")
@Getter
@Setter
@NoArgsConstructor
public class Prescription {
	
	@NotNull
	private Long doctorId;
	
	@NotNull
	private Long appointmentId;
	
	@NotNull
	private Long medicineId;
	
	@NotNull
	@Column(name = "prescription", length = 200)
	private String prescription;
	
	@NotNull
	@Positive
	private int quantity;
	
}
