package com.milankas.training.dtos.password;

import com.milankas.training.dtos.user.PostAddressInputDTO;
import com.milankas.training.validators.NoSpacesValidator.NoSpacesConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class PostCompanyInputDTO {

    private UUID id;

    @NotNull(message = "Name is required")
    @NoSpacesConstraint(message = "Name is must contain letter")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @Valid
    @NotNull(message = "Address is required")
    private PostAddressInputDTO address;

}
