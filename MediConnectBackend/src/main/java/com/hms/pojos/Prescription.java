package com.hms.pojos;

import java.util.ArrayList;
import java.util.List;

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
    private Long prescription_id ;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "docid")
    private Doctor doctorId; // Foreign key 
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointmentId; // Foreign key 
 
        
    @JoinColumn(name = "medicineid")
    
    private List<Long> medicines = new ArrayList<>(); // Many medicines can be prescribed

    @NotNull
    @Column(name = "prescription", length = 200)
    private String prescription_desc; // Description

    @NotNull
    @Positive
    private int quantity;
}
