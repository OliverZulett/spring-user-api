package com.milankas.training.dtos.password;

import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.validators.NoSpacesValidator.NoSpacesConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class PatchCompanyInputDTO {

    private UUID id;

    @NoSpacesConstraint(message = "Name is must contain letter")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @Valid
    private PatchAddressInputDTO address;

}
