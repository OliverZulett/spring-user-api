package com.milankas.training.Mappers.address;

import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.dtos.user.PostAddressInputDTO;
import com.milankas.training.dtos.user.AddressOutputDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapperInterface {

    AddressMapperInterface MAPPER = Mappers.getMapper( AddressMapperInterface.class );

    AddressOutputDTO EntityToDto(AddressEntity addressEntity);

    PatchAddressInputDTO EntityToPatchDto(AddressEntity addressEntity);

    AddressEntity PostDtoToEntity(PostAddressInputDTO addressDTO);

    @BeforeMapping
    default void validateAddressFields(@MappingTarget AddressEntity addressEntity, PatchAddressInputDTO addressDTO) {
        addressDTO.setId(addressEntity.getId());
        if (addressDTO.getAddressLine1() == null) addressDTO.setAddressLine1(addressEntity.getAddressLine1());
        if (addressDTO.getAddressLine2() == null) addressDTO.setAddressLine2(addressEntity.getAddressLine2());
        if (addressDTO.getState() == null) addressDTO.setState(addressEntity.getState());
        if (addressDTO.getCity() == null) addressDTO.setCity(addressEntity.getCity());
        if (addressDTO.getZipCode() == null) addressDTO.setZipCode(addressEntity.getZipCode());
        if (addressDTO.getCountryCode() == null) addressDTO.setCountryCode(addressEntity.getCountryCode());
    }
    AddressEntity PatchDtoToEntity(@MappingTarget AddressEntity addressEntity, PatchAddressInputDTO addressDTO);

}
