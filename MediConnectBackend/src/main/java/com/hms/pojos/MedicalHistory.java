package com.hms.pojos;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="medicalhistoryid")
	private Long medHistoryId ;
	
	 @NotNull
	 @ManyToOne
	 @JoinColumn(name = "patient_id")
	 private Patient patient; // Unidirectional relationship to Patient

	 @NotNull
	 @ManyToOne
	 @JoinColumn(name = "doctor_id")
	 private Doctor doctor; // Unidirectional relationship to Doctor
	
	@NotNull
	@OneToOne
	private Appointment appointmentId;
	
	@NotNull
	private String diagnosis;

}

