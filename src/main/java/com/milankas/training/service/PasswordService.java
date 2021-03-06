package com.milankas.training.service;

import com.milankas.training.exception.PasswordExistingException;
import com.milankas.training.persistance.entity.PasswordEntity;
import com.milankas.training.persistance.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    PasswordService passwordService;

    public List<PasswordEntity> generatePasswordRegister(String password) {
        return Arrays.asList(this.passwordService.passwordEncoder(password));
    }

    public List<PasswordEntity> updatePasswordRegister(List<PasswordEntity> passwordRegister, String newPassword) throws PasswordExistingException {
        if (newPassword == null) return passwordRegister;
        passwordRegister.forEach(passwordEntity -> passwordEntity.setStatus(0));
        if (BCrypt.checkpw(newPassword, passwordRegister.get(passwordRegister.size() - 1).getHash()))
            throw new PasswordExistingException("New password cannot be the same that the actual password");
        ;
        passwordRegister.add(this.passwordService.passwordEncoder(newPassword));
        return passwordRegister;
    }

    public PasswordEntity getCurrentPasswordByUserId(UUID userId) {
        return this.passwordRepository.findByUserIdAndStatus(userId, 1);
    }

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
