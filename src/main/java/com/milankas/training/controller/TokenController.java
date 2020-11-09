package com.milankas.training.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.milankas.training.dto.credential.PostCredentialDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.exception.UnauthorizedException;
import com.milankas.training.service.AuthenticationService;
import com.milankas.training.service.TokenService;
import com.milankas.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("v1")
public class TokenController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getToken(@Valid @RequestBody PostCredentialDTO userCredentials) throws UnauthorizedException, IOException {
        String token = this.authenticationService.login(userCredentials);
        if (token == null) throw new UnauthorizedException("Incorrect password");
        return token;
    }

    @GetMapping("/token-validator")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UserOutputDTO tokenValidator(@RequestHeader (name="Authorization") String token) throws IOException, UnauthorizedException {
        DecodedJWT decodedJWT = this.tokenService.validateToken(this.tokenService.getToken(token));
        if (decodedJWT == null) throw new UnauthorizedException("Invalid token");
        return this.userService.findUserById(UUID.fromString(decodedJWT.getSubject()));
    }

}
