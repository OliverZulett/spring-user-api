package com.milankas.training.dtos.password;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PasswordDTO {

    @NotNull(message = "id is required")
    private UUID id;

    @NotNull(message = "hash is required")
    private String hash;

    @NotNull(message = "salt is required")
    private String salt;

    @NotNull(message = "status is required")
    private Integer status;

}
