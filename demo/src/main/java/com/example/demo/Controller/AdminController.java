package com.example.demo.Controller;

import com.example.demo.Model.Doctor;
import com.example.demo.Model.Patient;
import com.example.demo.Service.DoctorService;
import com.example.demo.Service.PatientService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @PostMapping("doctors")
    public String addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("patients")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }
}
