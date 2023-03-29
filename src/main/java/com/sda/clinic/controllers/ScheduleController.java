package com.sda.clinic.controllers;

import com.sda.clinic.models.company.ScheduleDto;
import com.sda.clinic.models.company.medical_history.AppointmentDto;
import com.sda.clinic.security.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/getScheduleByDay")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    public ResponseEntity<ScheduleDto> getScheduleByDay(@RequestParam String date, @RequestParam String userUuid) {
        return ResponseEntity.ok(scheduleService.getScheduleByDate(date, userUuid));
    }

    @GetMapping("/getAppointmentByUuid")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    public ResponseEntity<AppointmentDto> getAppointmentByUuid(@RequestParam String uuid) {
        return ResponseEntity.ok(scheduleService.getAppointmentByUuid(uuid));
    }

    @PutMapping("/doctorSchedule")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<?> modifyByDoctor(@RequestBody AppointmentDto request) {
        scheduleService.modifyByDoctor(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/addPatientSchedule")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<?> addPatientSchedule(@RequestBody AppointmentDto request) {
        scheduleService.addPatientSchedule(request.getUuid(), request.getDoctor(), request.getPatient());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
