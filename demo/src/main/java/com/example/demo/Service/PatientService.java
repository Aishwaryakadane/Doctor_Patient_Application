package com.example.demo.Service;

import com.example.demo.Model.DTO.AuthenticationInputDto;
import com.example.demo.Model.DTO.SchedulerDto;
import com.example.demo.Model.DTO.SignInDto;
import com.example.demo.Model.Patient;
import com.example.demo.Model.PatientAuthenticationToken;
import com.example.demo.Repo.IPatientRepo;
import com.example.demo.Repo.ITokenRepo;
import com.example.demo.Service.EmailUtility.SendEmail;
import com.example.demo.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    TokenService tokenService;

    public String patientSignUp(Patient patient) {

        String newEmail = patient.getPatientEmail();

        Patient existingPatient = patientRepo.findFirstByPatientEmail(newEmail);

        if (existingPatient != null) {
            return "email already in use";
        }

        String signUpPassword = patient.getPatientPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            patient.setPatientPassword(encryptedPassword);

            patientRepo.save(patient);

            return "patient registered";
        } catch (NoSuchAlgorithmException e) {

            return "invalid credentials";
        }
    }

    public String patientSignIn(SignInDto signIn) {

        String email = signIn.getEmail();

        Patient existingPatient = patientRepo.findFirstByPatientEmail(email);

        if (existingPatient == null) {
            return "Not a valid email, Please sign up first !!!";
        }

        String signInPassword = signIn.getPassword();


        try {
          String  encryptedPassword = PasswordEncryptor.encrypt(signInPassword);


        if (existingPatient.getPatientPassword().equals(encryptedPassword)) {
                PatientAuthenticationToken token = new PatientAuthenticationToken(existingPatient);
                if (SendEmail.sendEmail(email, "otp", token.getTokenValue())) {
                    tokenService.createToken(token);
                    return "check email for otp/token!!!";
                } else {
                    return "token not send";
                }
            } else {
                return "invalid credentials";
            }

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue";
        }

    }

    public String patientSignOut(AuthenticationInputDto authInfo) {

        if(tokenService.authenticate(authInfo)){
            String tokenValue =authInfo.getTokenValue();
            tokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
        }


    public List<Patient> getPatients() {
        return patientRepo.findAll();
    }
}