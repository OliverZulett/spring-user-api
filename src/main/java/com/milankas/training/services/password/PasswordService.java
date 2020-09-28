package com.milankas.training.services.password;

import com.milankas.training.persistance.entities.PasswordEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Arrays;
import java.util.List;

public class PasswordService implements PasswordServiceInterface {

    @Override
    public List<PasswordEntity> generatePasswordRegister(String password) {
        return Arrays.asList(passwordEncoder(password));
    }

    private PasswordEntity passwordEncoder(String password) {
        PasswordEntity passwordEncoded = new PasswordEntity();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        passwordEncoded.setSalt(salt);
        passwordEncoded.setHash(hash);
        passwordEncoded.setStatus(1);
        return passwordEncoded;
    }

}
