package com.milankas.training.services.address;

import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.dtos.user.PostAddressInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class AddressService implements AddressServiceInterface {

    @Autowired
    AddressMapperInterface addressMapper;

    @Override
    public AddressEntity validatePostAddressToSave(@Valid PostAddressInputDTO address) {
        return addressMapper.PostDtoToEntity(address);
    }

    @Override
    public AddressEntity validatePatchAddressToSave(AddressEntity address, PatchAddressInputDTO addressForUpdate) {
        if (addressForUpdate == null) return address;
        return addressMapper.PatchDtoToEntity(address, addressForUpdate);
    }

}
