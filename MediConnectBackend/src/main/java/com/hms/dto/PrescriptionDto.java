package com.hms.dto;

import java.util.List;

import com.hms.pojos.Appointment;
import com.hms.pojos.Doctor;
import com.hms.pojos.MedicineRecord;

public class PrescriptionDto {
	

	private Doctor doctorId; //foriengn key 
	
	private Appointment appointmentId; //foriegn key 

	private List<MedicineRecord> medicineRecords ;

	private List<MedicineRecord> medicineId ; //foriegn key  //one to many
	
	private String prescription_desc; //text

	private int quantity;

}
