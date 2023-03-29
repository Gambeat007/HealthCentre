package com.sda.clinic.models.company.clinic;

import com.sda.clinic.models.company.PhoneTypeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ClinicPhoneDto {
    private UUID uuid;
    private UUID clinicUuid; //FK
    private PhoneTypeDto phoneType; //FK
    private String phone;
    private boolean primary;

    public static ClinicPhoneDto map(ClinicPhone entity) {
        return ClinicPhoneDto.builder()
                .uuid(entity.getUuid())
                .clinicUuid(entity.getClinic().getUuid())
                .phoneType(entity.getPhoneType() == null ? null : PhoneTypeDto.map(entity.getPhoneType()))
                .phone(entity.getPhone())
                .primary(entity.isPrimary())
                .build();
    }

}
