package com.example.demo.Repo;

import com.example.demo.Model.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<PatientAuthenticationToken,Long>{

    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);

}