package com.hms.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@AllArgsConstructor
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Prescription_id ;
	

	@NotNull
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctorId; //foriengn key 
	
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "appoinment_id")
	private Appointment appointmentId; //foriegn key 
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "medicine_id")
	private List<MedicineRecord> medicineRecords = new ArrayList<>();
	
	@NotNull
	@Column(name = "prescription", length = 200)
	private String prescription_desc; //text
	
	@NotNull
	@Positive
	private int quantity;
	
}
