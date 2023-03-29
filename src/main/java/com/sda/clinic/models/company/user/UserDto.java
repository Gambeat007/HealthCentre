package com.sda.clinic.models.company.user;

import com.sda.clinic.models.company.DictionaryItemDto;
import com.sda.clinic.models.company.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {
    private String uuid;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private UserAppDetailsDto userAppDetails;
    private String clinic;

    public static UserDto map(User entity) {
        return UserDto.builder()
                .uuid(entity.getUuid().toString())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .userAppDetails(entity.getUserAppDetails() == null ? null : UserAppDetailsDto.map(entity.getUserAppDetails()))
                .clinic((entity.getClinic() == null) ? null : entity.getClinic().getUuid().toString())
                .build();
    }

    public static DictionaryItemDto dictionary(User entity) {
        String itemName = entity.getUserAppDetails().getFirstName() +
                " " +
                (entity.getUserAppDetails().getSecondName() == null ? "" : entity.getUserAppDetails().getSecondName()) +
                " " +
                entity.getUserAppDetails().getLastName() +
                " (PESEL: " +
                entity.getUserAppDetails().getPesel() +
                ")";
        itemName.trim().replaceAll(" +", " ");
        return new DictionaryItemDto(entity.getUuid().toString(), itemName, entity.getStatus().toString());

    }
}
