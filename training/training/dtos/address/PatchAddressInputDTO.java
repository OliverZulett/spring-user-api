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
public class PatchAddressInputDTO {

    private UUID id;

    @Size(min = 2, max = 100, message = "Line address 1 must be between 2 and 100 characters")
    private String addressLine1;

    @Size(min = 2, max = 100, message = "Line address 2 must be between 2 and 100 characters")
    private String addressLine2;

    @Size(min = 2, max = 100, message = "Contact name must be between 2 and 100 characters")
    private String contactName;

    @Size(min = 5, max = 8, message = "Contact phone must be between 5 and 8 numbers")
    private String contactPhone;

    @Size(min = 2, max = 20, message = "State must be between 2 and 20 characters")
    private String state;

    @Size(min = 2, max = 20, message = "City 1 must be between 2 and 20 characters")
    private String city;

    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code")
    private String zipCode;

    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    @CountryCodeConstraint
    private String countryCode;

}
