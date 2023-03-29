package com.sda.clinic.models.company;

import com.sda.clinic.models.company.clinic.ClinicDto;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CompanyDto {

    private UUID uuid;
    private String fullName;
    private String shortName;

    private String nip;
    private String regon;
    private String krs;
    private String accountNumber;
    private String province;
    private String district;
    private String community;
    private String locality;
    private String street;
    private String streetNo;
    private String flatNo;
    private String postCode;
    private String post;

    private float longitude;
    private float latitude;

    private Set<CompanyEmailDto> emails;
    private Set<CompanyPhoneDto> phones;
    private Set<UUID> clinics;

    public static CompanyDto map(Company entity) {
        return CompanyDto.builder()
                .uuid(entity.getUuid())
                .fullName(entity.getFullName())
                .shortName(entity.getShortName())
                .nip(entity.getNip())
                .regon(entity.getRegon())
                .krs(entity.getKrs())
                .accountNumber(entity.getAccountNumber())
                .province(entity.getProvince())
                .district(entity.getDistrict())
                .community(entity.getCommunity())
                .locality(entity.getLocality())
                .street(entity.getStreet())
                .streetNo(entity.getStreetNo())
                .flatNo(entity.getFlatNo())
                .postCode(entity.getPostCode())
                .post(entity.getPost())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .emails((entity.getEmails() == null) ? null : entity.getEmails().stream().map(CompanyEmailDto::map).collect(Collectors.toSet()))
                .phones((entity.getPhones() == null) ? null : entity.getPhones().stream().map(CompanyPhoneDto::map).collect(Collectors.toSet()))
                .clinics((entity.getClinics() == null) ? null : entity.getClinics().stream().map(CompanyBase::getUuid).collect(Collectors.toSet()))
                .build();
    }
}
