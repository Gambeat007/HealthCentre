package com.sda.clinic.models.company.medical_history;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AppointmentDto {

    private UUID uuid;
    private UUID patient;
    private UUID doctor;
    private UUID clinic;

    private String description;
    private String recommendations;

    private SickLeaveDto sickLeave;
    private PrescriptionDto prescription;
    private ReferralDto referral;

    public static AppointmentDto map(Appointment entity) {
        return AppointmentDto.builder()
                .uuid(entity.getUuid())
                .patient(entity.getPatient() == null ? null : entity.getPatient().getUuid())
                .doctor(entity.getDoctor() == null ? null : entity.getDoctor().getUuid())
                .clinic(entity.getClinic() == null ? null : entity.getClinic().getUuid())
                .description(entity.getDescription())
                .recommendations(entity.getRecommendations())
                .sickLeave(entity.getSickLeave() == null ? null : SickLeaveDto.map(entity.getSickLeave()))
                .prescription(entity.getPrescription() == null ? null : PrescriptionDto.map(entity.getPrescription()))
                .referral(entity.getReferral() == null ? null : ReferralDto.map(entity.getReferral()))
                .build();
    }

}