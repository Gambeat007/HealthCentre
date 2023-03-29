package com.sda.clinic.controllers;

import com.sda.clinic.models.company.DictionaryItemsDto;
import com.sda.clinic.models.company.clinic.ClinicDto;
import com.sda.clinic.security.services.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;

    @GetMapping("/getAllClinics")
    public ResponseEntity<Page<ClinicDto>> getAllClinics(@RequestParam Integer page) {
        return ResponseEntity.ok(clinicService.getAll(page));
    }

    @GetMapping("/getDictionaryClinic")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<DictionaryItemsDto> getDictionaryClinic() {
        return ResponseEntity.ok(clinicService.getAsDictionary());
    }
}
