package com.example.demo.Repo;

import com.example.demo.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment,Integer> {

}
