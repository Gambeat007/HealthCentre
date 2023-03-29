package com.sda.clinic.models.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DictionaryItemDto {
    private String uuid;
    private String itemName;
    private String status;
}
