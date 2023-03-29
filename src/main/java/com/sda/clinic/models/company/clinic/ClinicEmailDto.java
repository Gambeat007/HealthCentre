package com.sda.clinic.models.company.clinic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClinicEmailDto {
    private ClinicDto clinic; //FK
    private String email;
    private boolean primary;

    public static ClinicEmailDto map(ClinicEmail entity) {
        return ClinicEmailDto.builder()
                .email(entity.getEmail())
                .primary(entity.isPrimary())
                .build();
    }

}
