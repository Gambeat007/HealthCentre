package com.sda.clinic.security.services;

import com.sda.clinic.models.company.ScheduleDto;
import com.sda.clinic.models.company.doctor.DoctorCalendar;
import com.sda.clinic.models.company.doctor.DoctorCalendarDto;
import com.sda.clinic.models.company.medical_history.Appointment;
import com.sda.clinic.models.company.medical_history.AppointmentDto;
import com.sda.clinic.models.company.role.RoleType;
import com.sda.clinic.models.company.user.User;
import com.sda.clinic.repository.AppointmentRepository;
import com.sda.clinic.repository.ScheduleRepository;
import com.sda.clinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public ScheduleDto getScheduleByDate(String date, String userUuid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime =
                LocalDate.parse(date, formatter).atStartOfDay();
        LocalDateTime dateFrom = dateTime.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime dateTo = dateFrom.plusDays(1);
        if (userUuid == null || userUuid.isBlank()) {
            return new ScheduleDto(new ArrayList<>(), dateFrom.toString());
        }
        UUID uuid = UUID.fromString(userUuid);
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Nie " +
                        "znaleziono użytkownika!!!"));
        Set<DoctorCalendar> doctorCalendar =
                scheduleRepository.findByDateRange(dateFrom, dateTo, user);
        return new ScheduleDto(doctorCalendar.stream()
                .map(DoctorCalendarDto::map)
                .collect(Collectors.toList()), dateFrom.toString());
    }

    public void modifyByDoctor(AppointmentDto request) {
        if (request.getUuid() == null) {
            throw new IllegalArgumentException("Błąd przesłania danych!!!");
        }
        Appointment appointment =
                appointmentRepository.findByUuid(request.getUuid())
                        .orElseThrow(() -> new IllegalArgumentException("Nie " +
                                "znaleziono terminu!!!"));
        appointment.setDescription(request.getDescription());
        appointment.setRecommendations(request.getRecommendations());
        appointmentRepository.saveAndFlush(appointment);
    }

    public AppointmentDto getAppointmentByUuid(String scheduleUuid) {
        if (scheduleUuid == null || scheduleUuid.isBlank()) {
            return AppointmentDto.map(new Appointment());
        }
        UUID uuid = UUID.fromString(scheduleUuid);
        Appointment appointment = appointmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Nie " +
                        "znaleziono terminu!!!"));
        return AppointmentDto.map(appointment);
    }

    public void addPatientSchedule(UUID scheduleUuid, UUID doctorUuid,
                                   UUID patientUuid) {
        if (scheduleUuid == null) {
            throw new IllegalArgumentException("Nie znaleziono wolnego " +
                    "terminu!!!");
        }
        if (doctorUuid == null) {
            throw new IllegalArgumentException("Nie znaleziono lekarza!!!");
        }
        if (patientUuid == null) {
            throw new IllegalArgumentException("Nie znaleziono wolnego " +
                    "pacjenta!!!");
        }
        DoctorCalendar schedule =
                scheduleRepository.findByUuid(scheduleUuid)
                        .orElseThrow(() -> new IllegalArgumentException("Nie " +
                                "znaleziono terminu!!!"));
        User doctor =
                userRepository.findByUuidAndRole(doctorUuid,
                                RoleType.ROLE_DOCTOR)
                        .orElseThrow(() -> new IllegalArgumentException("Nie " +
                                "znaleziono lekarza!!!"));
        User patient =
                userRepository.findByUuidAndRole(patientUuid
                                , RoleType.ROLE_PATIENT)
                        .orElseThrow(() -> new IllegalArgumentException("Nie " +
                                "znaleziono pacjenta!!!"));
        Appointment appointment = new Appointment(patient, doctor,
                doctor.getClinic(), null, null, null, null, null);
        appointment.setCreatedBy(patientUuid);
        appointment.setUpdatedBy(patientUuid);
        schedule.setAppointment(appointment);
        schedule.setUpdatedBy(patient.getUuid());
        scheduleRepository.saveAndFlush(schedule);
    }
}
