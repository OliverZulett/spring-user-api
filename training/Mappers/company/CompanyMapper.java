package com.milankas.training.Mappers.company;

import com.milankas.training.dtos.password.CompanyOutputDTO;
import com.milankas.training.dtos.password.PatchCompanyInputDTO;
import com.milankas.training.dtos.password.PostCompanyInputDTO;

public class CompanyMapper implements CompanyMapperInterface {

    @Override
    public CompanyOutputDTO EntityToDto(CompanyEntity companyEntity) {
        return CompanyMapperInterface.MAPPER.EntityToDto(companyEntity);
    }

    @Override
    public CompanyEntity PostDtoToEntity(PostCompanyInputDTO companyDTO) {
        return CompanyMapperInterface.MAPPER.PostDtoToEntity(companyDTO);
    }

    @Override
    public CompanyEntity PatchDtoToEntity(CompanyEntity companyEntity, PatchCompanyInputDTO companyDTO) {
        return CompanyMapperInterface.MAPPER.PatchDtoToEntity(companyEntity, companyDTO);
    }

}
