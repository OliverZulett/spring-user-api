package com.milankas.training.controllers;

import com.milankas.training.dtos.password.PatchCompanyInputDTO;
import com.milankas.training.dtos.password.PostCompanyInputDTO;
import com.milankas.training.dtos.password.CompanyOutputDTO;
import com.milankas.training.exceptions.ResourceNotFoundException;
import com.milankas.training.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyOutputDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyOutputDTO> getCompanyById(@Valid @PathVariable(value = "id") UUID companyId) throws ResourceNotFoundException {
        CompanyOutputDTO companyFound = companyService.findCompanyById(companyId);
        if (companyFound == null) throw new ResourceNotFoundException("Company not found for id: " + companyId);
        return ResponseEntity.ok(companyFound);
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyOutputDTO> createCompany(@Valid @RequestBody PostCompanyInputDTO companyToSave) {
        return new ResponseEntity<>(companyService.saveCompany(companyToSave), HttpStatus.CREATED);
    }

    @PatchMapping("/companies/{id}")
    public ResponseEntity<CompanyOutputDTO> updateCompany(@Valid @PathVariable(value = "id") UUID companyId, @Valid @RequestBody PatchCompanyInputDTO companyForUpdate) throws ResourceNotFoundException {
        CompanyOutputDTO companyUpdated = companyService.updateCompanyById(companyId, companyForUpdate);
        if (companyUpdated == null) throw new ResourceNotFoundException("Company not found for id: " + companyId);
        return new ResponseEntity<CompanyOutputDTO>(companyUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") UUID companyId) throws ResourceNotFoundException {
        Boolean companyDeleted = companyService.deleteCompanyById(companyId);
        if (companyDeleted == null) throw new ResourceNotFoundException("Company not found for id: " + companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
