package com.example.demo.Service;

import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.Qualification;
import com.example.demo.Model.Specialization;
import com.example.demo.Repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    IDoctorRepo doctorRepo;

    @Autowired
    TokenService tokenService;

    public String addDoctor(Doctor doctor) {
//        doctorRepo.save(doctor);
//        return "Doctor added";

        Integer docId = doctor.getDocId();

        if (docId != null && doctorRepo.existsById(docId)) {
            return "doctor already exists!!!";
        }

        doctor.setAppointments(null);// linking anyway does not happen from non fk side

        doctorRepo.save(doctor);

        return "doctor added!!!";
    }


    public List<Doctor> getDoctorByQualificationOrSpecialization(AuthenticationInputDto authInfo, Qualification qualification, Specialization specialization) {


        if (tokenService.authenticate(authInfo)) {

            List<Doctor> doctors = doctorRepo.findByDocQualificationOrDocSpecialization(qualification, specialization);
//
//            return doctors.stream().
//                    map(doc -> {
//                        doc.setAppointments(null);
//                        return doc;
//                    })
//                    .collect(Collectors.toList());


            for (Doctor doc : doctors) {
                doc.setAppointments(null);
            }

            return doctors;
        } else {
            return null;
        }
    }


    public List<Doctor> getAllDoctors(AuthenticationInputDto authInfo) {
        if (tokenService.authenticate(authInfo)) {
            return doctorRepo.findAll();
        } else {
            return null;
        }
    }


    public Doctor getDoctorById(Integer id) {

        return doctorRepo.findById(id).orElseThrow(null);
    }
}


