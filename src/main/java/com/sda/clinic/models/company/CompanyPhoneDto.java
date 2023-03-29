package com.sda.clinic.models.company;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CompanyPhoneDto extends CompanyBase {

    private UUID uuid;
    private UUID companyUuid;
    private PhoneTypeDto phoneType;
    private String phone;
    private boolean primary;

    public static CompanyPhoneDto map(CompanyPhone entity) {
        return CompanyPhoneDto.builder()
                .uuid(entity.getUuid())
                .companyUuid(entity.getCompany().getUuid())
                .phoneType(entity.getPhoneType() == null ? null : PhoneTypeDto.map(entity.getPhoneType()))
                .phone(entity.getPhone())
                .primary(entity.isPrimary())
                .build();
    }
}
