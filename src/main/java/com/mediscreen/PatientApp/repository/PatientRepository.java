package com.mediscreen.PatientApp.repository;

import com.mediscreen.PatientApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {}
