package com.mediscreen.PatientApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = " DiabeteApp", description = "Patient microservice"))
@SpringBootApplication
public class PatientAppApplication {

    public static void main(String[] args) {SpringApplication.run(PatientAppApplication.class, args);}
}
