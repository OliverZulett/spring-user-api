package com.milankas.training.Mappers.company;

import com.milankas.training.Mappers.address.AddressMapper;
import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.dtos.user.AddressOutputDTO;
import com.milankas.training.dtos.password.CompanyOutputDTO;
import com.milankas.training.dtos.password.PatchCompanyInputDTO;
import com.milankas.training.dtos.password.PostCompanyInputDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapperInterface {

    CompanyMapperInterface MAPPER = Mappers.getMapper(CompanyMapperInterface.class);

    AddressMapperInterface addressMapper = new AddressMapper();

    @Mappings({
            @Mapping(target = "address", source = "companyEntity", qualifiedByName = "addressEntityToDTO")
    })
    CompanyOutputDTO EntityToDto(CompanyEntity companyEntity);

    CompanyEntity PostDtoToEntity(PostCompanyInputDTO companyDTO);

    @BeforeMapping
    default void validateFields(@MappingTarget CompanyEntity companyEntity, PatchCompanyInputDTO companyDTO) {
        companyDTO.setId(companyEntity.getId());
        if (companyDTO.getName() == null) companyDTO.setName(companyEntity.getName());
    }
    CompanyEntity PatchDtoToEntity(@MappingTarget CompanyEntity companyEntity, PatchCompanyInputDTO companyDTO);

    @Named("addressEntityToDTO")
    public static AddressOutputDTO addressEntityToDTO(CompanyEntity company) {
        return addressMapper.EntityToDto(company.getAddress());
    }

}
