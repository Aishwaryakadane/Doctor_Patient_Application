package com.example.demo.Controller;

import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.DTO.SchedulerDto;
import com.example.demo.Model.DTO.SignInDto;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.Patient;
import com.example.demo.Model.Qualification;
import com.example.demo.Model.Specialization;
import com.example.demo.Service.AppointmentService;
import com.example.demo.Service.DoctorService;
import com.example.demo.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("patient/SignUp")
    public String patientSignUp(@RequestBody @Validated  Patient patient){
        return patientService.patientSignUp(patient);
    }

    @PostMapping("patient/SignIn")
    public String patientSignIn(@RequestBody SignInDto signInDto){
        return patientService. patientSignIn(signInDto);
    }

    @DeleteMapping("patient/SignOut")
    public String patientSignOut(@RequestBody AuthenticationInputDto authInfo){
        return patientService.patientSignOut(authInfo);
    }

    @PostMapping("patient/appointment/schedule")
    public String patientAppointmentSchedule(@RequestBody SchedulerDto schedule){
        return appointmentService.scheduleAppointment(schedule.getAuthInfo(),schedule.getAppointment());
    }
    @DeleteMapping("patient/appointment/{id}/cancel")
    public String patientAppointmentCancel(@RequestBody AuthenticationInputDto auth,@PathVariable Integer id){
        return appointmentService.patientAppointmentCancel(auth,id);
    }

    @GetMapping("doctor/{qualification}/{specialization}")
    public List<Doctor> getDoctorByQualificationOrSpecialization(@RequestBody AuthenticationInputDto authInfo,@PathVariable Qualification qualification,@PathVariable Specialization specialization){
        return doctorService.getDoctorByQualificationOrSpecialization(authInfo,qualification,specialization);
    }


}

