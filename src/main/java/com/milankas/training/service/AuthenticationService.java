package com.milankas.training.service;

import com.milankas.training.dto.credential.PostCredentialDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    EncryptionService encryptionService;
    @Autowired
    TokenService tokenService;

    public String login(PostCredentialDTO userCredentials) throws UnauthorizedException, IOException {
        UserOutputDTO userStored = this.userService.findUserByEmail(userCredentials.getEmail());
        if (userStored == null) throw new UnauthorizedException("User not found");
        if (this.encryptionService.verifyPassword(this.passwordService.getCurrentPasswordByUserId(userStored.getUserId()), userCredentials.getPassword())) {
            return this.tokenService.getToken(userStored);
        }
        return "Incorrect password";
    }
}
