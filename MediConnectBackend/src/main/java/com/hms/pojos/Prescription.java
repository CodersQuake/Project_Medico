package com.hms.pojos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
import lombok.ToString;

@Entity
@Table(name = "Prescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId ;
    
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctorId; // Foreign key 
    
//    @NotNull
    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointmentId; // Foreign key 

//    // Correct Many-to-Many relationship with MedicineRecord (not Medicine directly)
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//        name = "prescription_medicine_record", // Join table between Prescription and MedicineRecord
//        joinColumns = @JoinColumn(name = "prescription_id"),
//        inverseJoinColumns = @JoinColumn(name = "medicineid")  // Link to MedicineRecord via medicineid
//    )
//    private Set<MedicineRecord> medicines = new HashSet<>(); // Many medicines can be prescribed

//    @NotNull
    @OneToMany
    private Set<MedicineRecord> medicines;
    
//    @NotNull
    @Column(name = "prescription", length = 200)
    private String prescriptionDesc; // Description

//    @NotNull
//    @Positive
    private int quantity;
}
