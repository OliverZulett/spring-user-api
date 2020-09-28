package com.milankas.training.dtos.password;

import com.milankas.training.dtos.user.AddressOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyOutputDTO {

    private UUID id;

    private String name;

    private AddressOutputDTO address;

}
