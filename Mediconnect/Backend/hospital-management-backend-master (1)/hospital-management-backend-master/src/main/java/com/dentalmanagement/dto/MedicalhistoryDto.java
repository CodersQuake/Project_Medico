package com.dentalmanagement.dto ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalhistoryDto {
	

	 private Long appointmentId;
	 
	 private String diagnosis;

}
