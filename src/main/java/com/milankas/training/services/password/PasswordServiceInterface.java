package com.milankas.training.services.password;

import com.milankas.training.persistance.entities.PasswordEntity;

import java.util.List;

public interface PasswordServiceInterface {

    List<PasswordEntity> generatePasswordRegister(String password);

}
