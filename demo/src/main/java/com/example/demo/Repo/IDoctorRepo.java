package com.example.demo.Repo;

import com.example.demo.Model.Doctor;
import com.example.demo.Model.Qualification;
import com.example.demo.Model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByDocQualificationOrDocSpecialization(Qualification qualification, Specialization specialization);
}

