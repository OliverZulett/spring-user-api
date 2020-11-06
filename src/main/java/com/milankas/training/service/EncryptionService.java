package com.milankas.training.service;

import com.milankas.training.persistance.entity.PasswordEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    public Boolean verifyPassword(PasswordEntity passwordEntity, String password) {
        return BCrypt.checkpw(password, passwordEntity.getHash());
    }

    public PasswordEntity passwordEncoder(String password) {
        PasswordEntity passwordEncoded = new PasswordEntity();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        passwordEncoded.setSalt(salt);
        passwordEncoded.setHash(hash);
        passwordEncoded.setStatus(1);
        return passwordEncoded;
    }

}
