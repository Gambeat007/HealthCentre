package com.sda.clinic.models.company;

import lombok.*;

@Getter
@Setter
@Builder
public class PhoneTypeDto extends CompanyBase {
    private String type;

    public static PhoneTypeDto map(PhoneType entity) {
        return PhoneTypeDto.builder()
                .type(entity.getType())
                .build();
    }

    public static DictionaryItemDto dictionary(PhoneType entity) {
        return new DictionaryItemDto(entity.getUuid().toString(), entity.getType(), entity.getStatus().toString());
    }

}
