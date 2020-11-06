package com.milankas.training.dto.credential;

import com.milankas.training.validator.noSpacesValidator.NoSpacesConstraint;
import lombok.Data;

import javax.validation.constraints.Size;

public @Data
class PostCredentialDTO {

    @NoSpacesConstraint(message = "Email must contain characters")
    @Size(min = 2, max = 100, message = "Email must be between 2 and 100 characters")
    private String email;

    @NoSpacesConstraint(message = "Password must contain characters")
    @Size(min = 2, max = 20, message = "Password must be between 5 and 20 characters")
    private String password;

}
