package com.sda.clinic.repository;

import com.sda.clinic.models.company.doctor.DoctorCalendar;
import com.sda.clinic.models.company.medical_history.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByUuid(UUID uuid);
}
