package com.mediscreen.PatientApp.controller;

import com.mediscreen.PatientApp.exceptions.PatientNotFoundException;
import com.mediscreen.PatientApp.model.Patient;
import com.mediscreen.PatientApp.repository.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @Operation(summary = "Get a list of all patients", description = "return a list of all patients in database")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "All patients returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "400", description = "No patients found", content = @Content)
    })
    @GetMapping("/patient/list")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @Operation(summary = "Add a new patient", description = "Add new patient in database")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient successfully added" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "400", description = "Error while adding patient", content = @Content)
    })
    @PostMapping("/patient/add")
    public void addPatient(@RequestBody @Valid Patient patient){
        patientRepository.save(patient);
        }




    @Operation(summary = "Get patient by firstname and lastname", description = "Get a patient with his firstname and lastmane")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient found in database" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "404", description = "Patient not found", content = @Content)
    })
    @GetMapping("/patient/get")
    public Patient getPatientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname, Model model){
        return patientRepository.getPatientByFirstnameAndLastname(firstname,lastname);
        }

    @Operation(summary = "Get patient by Id", description = "Get a patient with his id (integer)")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient found in database" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "404", description = "Patient not found", content = @Content)
    })
    @GetMapping("/patient/get/{id}")
    public Patient getPatientById (@PathVariable Integer id, Model model){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.get();
    }


    @Operation(summary = "Update patient infos", description = "Update a patient with his id (integer)")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient successfully updated" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "500", description = "Error while updating patient", content = @Content)
    })
    @PutMapping ("/patient/update/{id}")
    public void updatePatientInfos(@PathVariable Integer id, @RequestBody Patient patient){
        patient.setId(id);
        patientRepository.save(patient);
        }
}
