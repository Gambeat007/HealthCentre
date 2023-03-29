package com.sda.clinic.models.company.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserAppDetailsDto {
    private String uuid;
    private String firstName;
    private String secondName;
    private String lastName;
    private String pesel;
    private String idNumber;
    private LocalDate birthDay;
    private String nip;

    public static UserAppDetailsDto map(UserAppDetails entity) {
        return UserAppDetailsDto.builder()
                .uuid(entity.getUuid().toString())
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .lastName(entity.getLastName())
                .pesel(entity.getPesel())
                .idNumber(entity.getIdNumber())
                .birthDay(entity.getBirthDay())
                .nip(entity.getNip())
                .build();
    }

}
