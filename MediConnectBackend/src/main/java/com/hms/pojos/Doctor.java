package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor

public class Doctor extends User{

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
	

}
