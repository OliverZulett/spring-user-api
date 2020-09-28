package com.milankas.training.dto.user;

import com.milankas.training.validator.NoSpacesValidator.NoSpacesConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class PatchUserInputDTO {

    private UUID id;

    @NoSpacesConstraint(message = "First name must contain characters")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @NoSpacesConstraint(message = "Last name must contain characters")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @NoSpacesConstraint(message = "Email must contain characters")
    @Size(min = 2, max = 20, message = "Email must be between 2 and 20 characters")
    private String email;

    @NoSpacesConstraint(message = "Password must contain characters")
    @Size(min = 2, max = 20, message = "Password must be between 5 and 10 characters")
    private String password;

}
