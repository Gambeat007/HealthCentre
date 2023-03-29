package com.sda.clinic.models.company.medical_history;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SickLeaveDto {

    private UUID uuid;
    private String series;
    private String number;
    private String pesel;
    private String patient;
    private String insuredType;
    private LocalDate birthDayInsured;
    private String postCode;
    private String post;
    private String street;
    private String streetNo;
    private String flatNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate hospitalStartDate;
    private LocalDate hospitalEndDate;
    private String indicationType;
    private String code1;
    private String code2;
    private String code3;
    private String code4;
    private DiseaseTypeDto diseaseType;

    public static SickLeaveDto map(SickLeave entity) {
        return SickLeaveDto.builder()
                .uuid(entity.getUuid())
                .series(entity.getSeries())
                .number(entity.getNumber())
                .pesel(entity.getPesel())
                .patient(entity.getPatient())
                .insuredType(entity.getInsuredType() == null ? null : entity.getInsuredType().toString())
                .birthDayInsured(entity.getBirthDayInsured())
                .postCode(entity.getPostCode())
                .post(entity.getPost())
                .street(entity.getStreet())
                .streetNo(entity.getStreetNo())
                .flatNo(entity.getFlatNo())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .hospitalStartDate(entity.getHospitalStartDate())
                .hospitalEndDate(entity.getHospitalEndDate())
                .indicationType(entity.getIndicationType() == null ? null : entity.getIndicationType().toString())
                .code1(entity.getCode1() == null ? null : entity.getCode1().toString())
                .code2(entity.getCode2() == null ? null : entity.getCode2().toString())
                .code3(entity.getCode3() == null ? null : entity.getCode3().toString())
                .code4(entity.getCode4() == null ? null : entity.getCode4().toString())
                .diseaseType(entity.getDiseaseType() == null ? null : DiseaseTypeDto.map(entity.getDiseaseType()))
                .build();
    }

}
