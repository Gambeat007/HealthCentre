package com.sda.clinic.controllers;

import com.sda.clinic.models.company.CompanyDto;
import com.sda.clinic.security.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/getCompany")
    public ResponseEntity<CompanyDto> getAllCompanies() {
        return ResponseEntity.ok(companyService.getFirst());
    }
}
