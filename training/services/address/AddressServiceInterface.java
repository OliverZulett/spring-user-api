package com.milankas.training.services.address;

import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.dtos.user.PostAddressInputDTO;

public interface AddressServiceInterface {

    AddressEntity validatePostAddressToSave(PostAddressInputDTO address);

    AddressEntity validatePatchAddressToSave(AddressEntity address, PatchAddressInputDTO addressForUpdate);

}
