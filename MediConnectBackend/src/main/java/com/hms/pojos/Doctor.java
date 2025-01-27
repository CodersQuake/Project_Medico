package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Doctor")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	
	@Column(name = "specialization", nullable = false, length = 150)
	@NotNull
	@Size(min = 5, max = 50, message = "The Size Should Be More Than 5 and Less Than 50")
	private String specialization;
	
	@NotNull
	@Positive(message = "The Experience should be in Positive Term...")
	private int experience;
	
	@NotNull
	private String qualification;
	
	@NotNull
	private int capacity;
	
	public Doctor(Long docId, String speci, int exp, String qual, int cap) {
		this.doctorId = docId;
		this.specialization = speci;
		this.experience = exp;
		this.qualification = qual;
		this.capacity = cap;
	}

}
