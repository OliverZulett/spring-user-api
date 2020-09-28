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
public class PostAddressInputDTO {

    private UUID id;

    @NotNull(message = "Line address 1 is required")
    @NoSpacesConstraint(message = "Line address 1 must contain characters")
    @Size(min = 2, max = 100, message = "Line address 1 must be between 2 and 100 characters")
    private String addressLine1;

    @NotNull(message = "Line address 2 is required")
    @NoSpacesConstraint(message = "Line address 2 must contain characters")
    @Size(min = 2, max = 100, message = "Line address 2 must be between 2 and 100 characters")
    private String addressLine2;

    @NotNull(message = "State is required")
    @NoSpacesConstraint(message = "State must contain characters")
    @Size(min = 2, max = 20, message = "State must be between 2 and 20 characters")
    private String state;

    @NotNull(message = "City is required")
    @NoSpacesConstraint(message = "City must contain characters")
    @Size(min = 2, max = 20, message = "City 1 must be between 2 and 20 characters")
    private String city;

    @NotNull(message = "Zip code is required")
    @NoSpacesConstraint(message = "Zip code must contain characters")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code")
    private String zipCode;

    @NotNull(message = "Country code is required")
    @NoSpacesConstraint(message = "Country code must contain characters")
    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    @CountryCodeConstraint
    private String countryCode;

}
