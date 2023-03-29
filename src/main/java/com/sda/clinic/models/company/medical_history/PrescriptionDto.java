package com.sda.clinic.models.company.medical_history;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PrescriptionDto {

    private UUID uuid;
    private String code;
    private String accessCode;
    private LocalDate issueDate;
    private String patient;
    private String doctor;
    private String pzw;
    private String phone;
    private LocalDateTime controlDate;
    private String number;
    private String drug;
    private LocalDate realizationDate;
    private String packagingType;
    private String dosage;

    public static PrescriptionDto map(Prescription entity) {
        return PrescriptionDto.builder()
                .uuid(entity.getUuid())
                .code(entity.getCode())
                .accessCode(entity.getAccessCode())
                .issueDate(entity.getIssueDate())
                .patient(entity.getPatient())
                .doctor(entity.getDoctor())
                .pzw(entity.getPzw())
                .phone(entity.getPhone())
                .controlDate(entity.getControlDate())
                .number(entity.getNumber())
                .drug(entity.getDrug())
                .realizationDate(entity.getRealizationDate())
                .packagingType(entity.getPackagingType())
                .dosage(entity.getDosage())
                .build();
    }

}