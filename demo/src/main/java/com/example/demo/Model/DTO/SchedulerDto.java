package com.example.demo.Model.DTO;

import com.example.demo.Model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerDto {

    AuthenticationInputDto authInfo;
    Appointment appointment;
}
