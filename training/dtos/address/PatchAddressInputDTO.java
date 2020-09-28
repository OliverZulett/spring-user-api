package com.milankas.training.dtos.user;

import com.milankas.training.validators.CountryCodeValidator.CountryCodeConstraint;
import com.milankas.training.validators.NoSpacesValidator.NoSpacesConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class PatchAddressInputDTO {

    private UUID id;

    @NoSpacesConstraint(message = "Line address 1 must contain characters")
    @Size(min = 2, max = 100, message = "Line address 1 must be between 2 and 100 characters")
    private String addressLine1;

    @NoSpacesConstraint(message = "Line address 2 must contain characters")
    @Size(min = 2, max = 100, message = "Line address 2 must be between 2 and 100 characters")
    private String addressLine2;

    @NoSpacesConstraint(message = "State must contain characters")
    @Size(min = 2, max = 20, message = "State must be between 2 and 20 characters")
    private String state;

    @NoSpacesConstraint(message = "City must contain characters")
    @Size(min = 2, max = 20, message = "City 1 must be between 2 and 20 characters")
    private String city;

    @NoSpacesConstraint(message = "Zip code must contain characters")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code")
    private String zipCode;

    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    @NoSpacesConstraint(message = "Country code must contain characters")
    @CountryCodeConstraint
    private String countryCode;

}
