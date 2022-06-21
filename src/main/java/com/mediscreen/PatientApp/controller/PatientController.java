package com.mediscreen.PatientApp.controller;

import com.mediscreen.PatientApp.model.Patient;
import com.mediscreen.PatientApp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

}
