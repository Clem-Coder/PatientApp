package com.mediscreen.PatientApp.controller;

import com.mediscreen.PatientApp.model.Patient;
import com.mediscreen.PatientApp.repository.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    private static final Logger logger = LogManager.getLogger("PatientController");

    @Autowired
    PatientRepository patientRepository;

    /**
     * Get all patients
     *
     * @return a list of all patients
     */

    @Operation(summary = "Get a list of all patients", description = "return a list of all patients in database")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "All patients returned" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "400", description = "No patients found", content = @Content)
    })
    @GetMapping("/patient/list")
    public List<Patient> getAllPatients(){
        logger.info("New request: get all patients");
        return patientRepository.findAll();
    }



    /**
     * Register a new patient
     *
     * @param patient the patient to register
     */

    @Operation(summary = "Add a new patient", description = "Add new patient in database")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient successfully added" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "400", description = "Error while adding patient", content = @Content)
    })
    @PostMapping("/patient/add")
    public void addPatient(@RequestBody @Valid Patient patient){
        logger.info("New request: add a new patient");
        patientRepository.save(patient);
        }



    /**
     * Find a patient with firstname and lastname
     *
     * @param firstname the patient firstname
     * @param lastname the patient lastname
     *
     * @return a Patient object with all information about the patient
     */

    @Operation(summary = "Get patient by firstname and lastname", description = "Get a patient with his firstname and lastmane")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient found in database" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "404", description = "Patient not found", content = @Content)
    })
    @GetMapping("/patient/get")
    public Patient getPatientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname, Model model){
        logger.info("New request: get patient information for patient firstname: " + firstname + ", lastname: " + lastname);
        return patientRepository.getPatientByFirstnameAndLastname(firstname,lastname);
        }

    /**
     * Find a patient with his id
     *
     * @param id the patient id
     *
     * @return a Patient object with all information about the patient
     */

    @Operation(summary = "Get patient by Id", description = "Get a patient with his id (integer)")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient found in database" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "404", description = "Patient not found", content = @Content)
    })
    @GetMapping("/patient/get/{id}")
    public Patient getPatientById (@PathVariable Integer id, Model model){
        logger.info("New request: find patient by id with id: " + id);
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.get();
    }


    /**
     * Update patient infos
     *
     * @param id The id of the Patient
     * @param patient The patient with information updated
     */


    @Operation(summary = "Update patient infos", description = "Update a patient with his id (integer)")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Patient successfully updated" , content = {@Content(mediaType = "application/json", schema = @Schema(implementation =  Patient.class))}),
            @ApiResponse (responseCode = "500", description = "Error while updating patient", content = @Content)
    })
    @PutMapping ("/patient/update/{id}")
    public void updatePatientInfos(@PathVariable Integer id, @RequestBody Patient patient){
        logger.info("New request: update patient's information for patient id: " + id);
        patient.setId(id);
        patientRepository.save(patient);
        }
}
