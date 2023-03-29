package com.sda.clinic.models.company.medical_history;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReferralDto {

    private UUID uuid;
    private String locality;
    private LocalDate referralDate;
    private String clinicName;

    private String patientFullName;
    private int patientAge;
    private String patientAddress;
    private String patientPesel;
    private String patientPhone;

    private String diagnosis;
    private DiseaseTypeDto diseaseType;
    private String purpose;
    private String examinationsPerformed;

    public static ReferralDto map(Referral entity) {
        return ReferralDto.builder()
                .uuid(entity.getUuid())
                .locality(entity.getLocality())
                .referralDate(entity.getReferralDate())
                .clinicName(entity.getClinicName())
                .patientFullName(entity.getPatientFullName())
                .patientAge(entity.getPatientAge())
                .patientAddress(entity.getPatientAddress())
                .patientPesel(entity.getPatientPesel())
                .patientPhone(entity.getPatientPhone())
                .diagnosis(entity.getDiagnosis())
                .diseaseType(entity.getDiseaseType() == null ? null : DiseaseTypeDto.map(entity.getDiseaseType()))
                .purpose(entity.getPurpose())
                .examinationsPerformed(entity.getExaminationsPerformed())
                .build();
    }

}
