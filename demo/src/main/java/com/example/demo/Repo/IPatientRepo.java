package com.example.demo.Repo;

import com.example.demo.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient,Integer> {

    Patient findFirstByPatientEmail(String newEmail);
}

