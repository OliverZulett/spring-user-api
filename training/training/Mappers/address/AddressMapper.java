package com.milankas.training.Mappers.address;

import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.dtos.user.PostAddressInputDTO;
import com.milankas.training.dtos.user.AddressOutputDTO;

public class AddressMapper implements AddressMapperInterface {

    @Override
    public AddressOutputDTO EntityToDto(AddressEntity addressEntity) {
        return AddressMapperInterface.MAPPER.EntityToDto(addressEntity);
    }

    @Override
    public PatchAddressInputDTO EntityToPatchDto(AddressEntity addressEntity) {
        return AddressMapperInterface.MAPPER.EntityToPatchDto(addressEntity);
    }

    @Override
    public AddressEntity PostDtoToEntity(PostAddressInputDTO addressDTO) {
        return AddressMapperInterface.MAPPER.PostDtoToEntity(addressDTO);
    }

    @Override
    public AddressEntity PatchDtoToEntity(AddressEntity addressEntity, PatchAddressInputDTO addressDTO) {
        return AddressMapperInterface.MAPPER.PatchDtoToEntity(addressEntity, addressDTO);
    }

}
