package com.sda.clinic.models.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DictionaryItemsDto {
    private List<DictionaryItemDto> items;

}
