package com.milankas.training.dtos.user;

import com.milankas.training.validators.CountryCodeValidator.CountryCodeConstraint;
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
    @Size(min = 2, max = 100, message = "Line address 1 must be between 2 and 100 characters")
    private String addressLine1;

    @NotNull(message = "Line address 2 is required")
    @Size(min = 2, max = 100, message = "Line address 2 must be between 2 and 100 characters")
    private String addressLine2;

    @NotNull(message = "Contact name is required")
    @Size(min = 2, max = 100, message = "Contact name must be between 2 and 100 characters")
    private String contactName;

    @NotNull(message = "Contact phone is required")
    @Size(min = 5, max = 8, message = "Contact phone must be between 5 and 8 numbers")
    private String contactPhone;

    @NotNull(message = "State is required")
    @Size(min = 2, max = 20, message = "State must be between 2 and 20 characters")
    private String state;

    @NotNull(message = "City is required")
    @Size(min = 2, max = 20, message = "City 1 must be between 2 and 20 characters")
    private String city;

    @NotNull(message = "Zip code is required")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code")
    private String zipCode;

    @NotNull(message = "Country code is required")
    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    @CountryCodeConstraint
    private String countryCode;

}
