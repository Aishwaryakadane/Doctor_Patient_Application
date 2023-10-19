package com.example.demo.Service;

import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.PatientAuthenticationToken;
import com.example.demo.Repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    ITokenRepo tokenRepo;

    public boolean authenticate(AuthenticationInputDto authInfo) {

        String email = authInfo.getEmail();

        String tokenValue = authInfo.getTokenValue();

        PatientAuthenticationToken token = tokenRepo.findFirstByTokenValue(tokenValue);
        if (token != null) {
            return token.getPatient().getPatientEmail().equals(email);
        } else {
           return false;
        }
    }


    public void createToken(PatientAuthenticationToken token) {
        tokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {
        PatientAuthenticationToken token = tokenRepo.findFirstByTokenValue(tokenValue);
        tokenRepo.delete(token);
    }
}