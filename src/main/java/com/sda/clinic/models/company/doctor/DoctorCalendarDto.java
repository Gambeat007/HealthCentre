package com.sda.clinic.models.company.doctor;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DoctorCalendarDto {

    private UUID uuid;
    private UUID userUuid;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID appointment;

    public static DoctorCalendarDto map(DoctorCalendar entity) {
        return DoctorCalendarDto.builder()
                .uuid(entity.getUuid())
                .userUuid(entity.getUser().getUuid())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .appointment(entity.getAppointment() == null ? null : entity.getAppointment().getUuid())
                .build();
    }

}
