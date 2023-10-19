package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAToken")
public class PatientAuthenticationToken {

    @Id
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime creationTime;


    @OneToOne
    @JoinColumn(name = "fkey_patientId")
    private Patient patient;


    public PatientAuthenticationToken(Patient patient) {
        this.patient = patient;
        this.tokenValue= UUID.randomUUID().toString();
        this.creationTime=LocalDateTime.now();
    }


}
