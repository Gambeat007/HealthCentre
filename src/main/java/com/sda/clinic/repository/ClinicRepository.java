package com.sda.clinic.repository;

import com.sda.clinic.models.company.clinic.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Optional<Clinic> findByUuid(UUID fromString);

}
