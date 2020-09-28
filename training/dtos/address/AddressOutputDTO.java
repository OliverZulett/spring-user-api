package com.milankas.training.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressOutputDTO {

    private UUID id;

    private String addressLine1;

    private String addressLine2;

    private String state;

    private String city;

    private String zipCode;

    private String countryCode;

}
