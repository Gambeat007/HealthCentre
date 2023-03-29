package com.sda.clinic.models.company.doctor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DoctorDto {

    private UUID uuid;
    private UUID userUuid;
    private String pzw;
    private AcademicTitle academicTitle;

    public static DoctorDto map(Doctor entity) {
        return DoctorDto.builder()
                .uuid(entity.getUuid())
                .userUuid(entity.getUser().getUuid())
                .pzw(entity.getPzw())
                .academicTitle(entity.getAcademicTitle())
                .build();
    }
}
