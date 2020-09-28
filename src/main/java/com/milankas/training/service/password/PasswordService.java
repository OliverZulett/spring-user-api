package com.milankas.training.service.password;

import com.milankas.training.persistance.entities.PasswordEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PasswordService {

    public List<PasswordEntity> generatePasswordRegister(String password) {
        return Arrays.asList(passwordEncoder(password));
    }

    public List<PasswordEntity> updatePasswordRegister(List<PasswordEntity> passwordRegister, String newPassword) {
        passwordRegister.forEach(passwordEntity -> passwordEntity.setStatus(0));
        passwordRegister.add(passwordEncoder(newPassword));
        return passwordRegister;
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
