package com.sda.clinic.security.services;

import com.sda.clinic.constants.Constants;
import com.sda.clinic.models.company.DictionaryItemsDto;
import com.sda.clinic.models.company.clinic.ClinicDto;
import com.sda.clinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private final ClinicRepository repository;

    public Page<ClinicDto> getAll(int page) {
        return repository.findAll(PageRequest.of(page, Constants.DefaultPageSize)).map(ClinicDto::map);
    }

    public DictionaryItemsDto getAsDictionary() {
        return new DictionaryItemsDto(repository.findAll().stream()
                .map(ClinicDto::dictionary)
                .collect(Collectors.toList()));
    }
}
