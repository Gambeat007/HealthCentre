package com.sda.clinic.models.company;

import com.sda.clinic.models.company.doctor.DoctorCalendarDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleDto {
    private List<DoctorCalendarDto> items;
    private String date;
}
