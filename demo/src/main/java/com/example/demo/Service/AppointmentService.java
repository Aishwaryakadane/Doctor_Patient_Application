package com.example.demo.Service;

import com.example.demo.Model.Appointment;
import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.Patient;
import com.example.demo.Repo.IAppointmentRepo;
import com.example.demo.Repo.IDoctorRepo;
import com.example.demo.Repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo appointmentRepo;

    @Autowired
    IDoctorRepo doctorRepo;

    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    TokenService tokenService;


    public String scheduleAppointment(AuthenticationInputDto authInfo, Appointment appointment) {

        if(tokenService.authenticate(authInfo)) {

            String email = authInfo.getEmail();
            Patient mail = patientRepo.findFirstByPatientEmail(email);

            appointment.setPatient(mail);

            Integer docId = appointment.getDoctor().getDocId();
            Doctor doctor = doctorRepo.findById(docId).orElseThrow();

            appointment.setDoctor(doctor);

            if (doctor != null) {

                appointment.setAppCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "Appointment booked at time " + appointment.getAppScheduleTime() + "with doctor " + doctor.getDocName();
            } else {
                return "doc Id not found";
            }
        }
        else{
            return "un authenticate access";
        }


    }

    public String patientAppointmentCancel(AuthenticationInputDto auth, Integer id) {

        if(tokenService.authenticate(auth)){
            String email = auth.getEmail();
            Patient patient = patientRepo.findFirstByPatientEmail(email);
            Appointment appointment = appointmentRepo.findById(id).orElseThrow();
            if(appointment.getPatient().equals(patient)){
                appointmentRepo.deleteById(id);
                return "appointment with " + appointment.getDoctor().getDocName() + "has been canceled";
            }else{
                return "UnAuthorized cancel appointment!!";
            }
        }
        else{
            return "unAuthorized access";
        }
    }
}
