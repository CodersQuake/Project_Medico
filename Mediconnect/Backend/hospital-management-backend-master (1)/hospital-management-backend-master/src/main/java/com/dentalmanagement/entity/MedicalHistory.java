package com.dentalmanagement.entity ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
//	 @NotNull
//	 @ManyToOne
//	 @JoinColumn(name = "patient_id")
//	 private Patient patient; // Unidirectional relationship to Patient
//
//	 @NotNull
//	 @ManyToOne
//	 @JoinColumn(name = "doctor_id")
//	 private Doctor doctor; // Unidirectional relationship to Doctor
	
	 @NotNull
	 @OneToOne
	 @JoinColumn(name="appoinment_id")
	 private Appointment appointmentId; 
	
	 @NotNull
	 private String diagnosis;

}

