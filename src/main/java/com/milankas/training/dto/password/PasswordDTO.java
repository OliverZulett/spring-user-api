package com.milankas.training.dto.password;

import java.util.UUID;

public class PasswordDTO {

    private UUID id;

    private String hash;

    private String salt;

    private Integer status;

    private UUID userId;

}
