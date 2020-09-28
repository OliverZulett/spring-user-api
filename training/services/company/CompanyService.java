package com.milankas.training.services.company;

import com.milankas.training.Mappers.company.CompanyMapperInterface;
import com.milankas.training.dtos.password.CompanyOutputDTO;
import com.milankas.training.dtos.password.PatchCompanyInputDTO;
import com.milankas.training.dtos.password.PostCompanyInputDTO;
import com.milankas.training.persistance.repositories.CompanyRepository;
import com.milankas.training.services.address.AddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService implements CompanyServiceInterface {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyMapperInterface companyMapper;
    @Autowired
    AddressServiceInterface addressService;

    @Override
    public List<CompanyOutputDTO> findAllCompanies() {
        List<CompanyEntity> companysReceived = companyRepository.findAll();
        List<CompanyOutputDTO> companysToSend = new ArrayList();
        companysReceived.forEach(company -> {
            companysToSend.add(companyMapper.EntityToDto(company));
        });
        return companysToSend;
    }

    @Override
    public CompanyOutputDTO findCompanyById(UUID id) {
        return companyMapper.EntityToDto(companyRepository.findById(id).orElse(null));
    }

    @Override
    public CompanyOutputDTO saveCompany(@Valid PostCompanyInputDTO company) {
        CompanyEntity companyToSave = companyMapper.PostDtoToEntity(company);
        companyToSave.setAddress(addressService.validatePostAddressToSave(company.getAddress()));
        return companyMapper.EntityToDto(companyRepository.save(companyToSave));
    }

    @Override
    public CompanyOutputDTO updateCompanyById(UUID id, @Valid PatchCompanyInputDTO company) {
        CompanyEntity companyFound = companyRepository.findById(id).orElse(null);
        if (companyFound == null) return null;
        AddressEntity addressForUpdate = addressService.validatePatchAddressToSave(companyFound.getAddress(), company.getAddress());
        CompanyEntity companyToUpdate = companyMapper.PatchDtoToEntity(companyFound, company);
        companyToUpdate.setAddress(addressForUpdate);
        return companyMapper.EntityToDto(companyRepository.save(companyToUpdate));
    }

    @Override
    public Boolean deleteCompanyById(UUID id) {
        CompanyEntity companyFound = companyRepository.findById(id).orElse( null);
        if (companyFound == null) return null;
        companyRepository.delete(companyFound);
        return true;
    }

}
