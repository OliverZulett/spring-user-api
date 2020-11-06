package com.milankas.training.dto.user;

import com.milankas.training.dto.password.PasswordDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

public @Data
class UserAuthenticatedDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private List<PasswordDTO> password;

}
