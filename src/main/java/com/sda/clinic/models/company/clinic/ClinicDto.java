package com.sda.clinic.models.company.clinic;

import com.sda.clinic.models.company.DictionaryItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ClinicDto {

    private String uuid;
    private String company;
    private String province;
    private String district;
    private String community;
    private String locality;
    private String street;
    private String streetNo;
    private String flatNo;
    private String postCode;
    private String post;
    private String clinicName;
    private String description;

    private Set<ClinicEmailDto> emails;
    private Set<ClinicPhoneDto> phones;

    public static ClinicDto map(Clinic entity) {
        return ClinicDto.builder()
                .uuid(entity.getUuid().toString())
                .company(entity.getCompany().getUuid().toString())
                .province(entity.getProvince())
                .district(entity.getDistrict())
                .community(entity.getCommunity())
                .locality(entity.getLocality())
                .street(entity.getStreet())
                .streetNo(entity.getStreetNo())
                .flatNo(entity.getFlatNo())
                .postCode(entity.getPostCode())
                .post(entity.getPost())
                .clinicName(entity.getClinicName())
                .description(entity.getDescription())
                .emails((entity.getEmails() == null) ? null : entity.getEmails().stream()
                        .map(ClinicEmailDto::map)
                        .collect(Collectors.toSet()))
                .phones((entity.getPhones() == null) ? null : entity.getPhones().stream()
                        .map(ClinicPhoneDto::map)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static DictionaryItemDto dictionary(Clinic entity) {
        String itemName = entity.getClinicName() + " (" + entity.getLocality() + ")";
        return new DictionaryItemDto(entity.getUuid().toString(), itemName, entity.getStatus().toString());
    }

}
