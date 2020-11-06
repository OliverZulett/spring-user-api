package com.milankas.training.controller;

import com.milankas.training.dto.credential.PostCredentialDTO;
import com.milankas.training.exception.UnauthorizedException;
import com.milankas.training.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getToken(@Valid @RequestBody PostCredentialDTO userCredentials) throws UnauthorizedException {
        return this.authenticationService.login(userCredentials);
    }

}
