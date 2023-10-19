package com.example.demo.Controller;

import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.Doctor;
import com.example.demo.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    DoctorService doctorService;

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(@Valid @RequestBody AuthenticationInputDto authInfo)
    {
        return doctorService.getAllDoctors(authInfo);
    }


    @GetMapping("doctor/{id}")
    public Doctor getDoctorById(@PathVariable Integer id)
    {
        return doctorService.getDoctorById(id);
    }

}
