package com.milankas.training.services.company;

import com.milankas.training.dtos.password.CompanyOutputDTO;
import com.milankas.training.dtos.password.PatchCompanyInputDTO;
import com.milankas.training.dtos.password.PostCompanyInputDTO;

import java.util.List;
import java.util.UUID;

public interface CompanyServiceInterface {

    List<CompanyOutputDTO> findAllCompanies();

    CompanyOutputDTO findCompanyById(UUID id);

    CompanyOutputDTO saveCompany(PostCompanyInputDTO product);

    CompanyOutputDTO updateCompanyById(UUID id, PatchCompanyInputDTO productForUpdate);

    Boolean deleteCompanyById(UUID id);

}
