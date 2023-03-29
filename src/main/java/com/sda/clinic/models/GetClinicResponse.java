package com.sda.clinic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetClinicResponse {
    private String province;
    private String district;
    private String community;
    private String locality;
    private String street;
    private String streetNo;
    private String flatNo;
    private String postCode;
    private String post;
    private String clinic_name;
    private String description;
    private List<String> emails;
    private List<String> phoneNumbers;
}
