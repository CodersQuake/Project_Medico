package com.hms.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Medical_History")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class MedicalHistory {
	
	@NotNull
	private Long patientId;
	
	@NotNull
	private Long doctorId;
	
	@NotNull
	private Long appointmentId;
	
	@NotNull
	private String diagnosis;

}
