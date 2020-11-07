package com.milankas.training.dto.password;

import com.milankas.training.validator.noSpacesValidator.NoSpacesConstraint;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public @Data
class PasswordDTO {

    @NotNull(message = "Password is required")
    @NoSpacesConstraint(message = "Password must contain characters")
    @Size(min = 2, max = 20, message = "Password must be between 5 and 20 characters")
    private String oldPassword;

    @NotNull(message = "Password is required")
    @NoSpacesConstraint(message = "Password must contain characters")
    @Size(min = 2, max = 20, message = "Password must be between 5 and 20 characters")
    private String newPassword;

}
