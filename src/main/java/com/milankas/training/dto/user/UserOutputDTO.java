package com.milankas.training.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserOutputDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

}
