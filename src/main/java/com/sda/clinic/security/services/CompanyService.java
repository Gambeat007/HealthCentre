package com.sda.clinic.security.services;

import com.sda.clinic.models.company.Company;
import com.sda.clinic.models.company.CompanyDto;
import com.sda.clinic.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyDto getFirst() {
        Company company = companyRepository.findFirstByOrderByUuid()
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono firmy!!!"));
        return CompanyDto.map(company);
    }
}
