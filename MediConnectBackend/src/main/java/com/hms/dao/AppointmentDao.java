package com.hms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.Appointment;
import com.hms.pojos.Patient;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

}
