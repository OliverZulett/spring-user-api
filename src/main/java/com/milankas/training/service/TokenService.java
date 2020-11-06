package com.milankas.training.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.utils.PemUtils;
import org.bouncycastle.jce.interfaces.ECKey;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    public String getToken(UserOutputDTO user) throws IOException {
        RSAPublicKey publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile("./rsa/public.pem", "RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile("./rsa/private.pem", "RSA");
        Calendar jwtExpiredTime = Calendar.getInstance();
        jwtExpiredTime.setTime(new Date());
        jwtExpiredTime.add(Calendar.MINUTE, 5);
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withExpiresAt(jwtExpiredTime.getTime())
                    .withIssuer("milankas.api")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            System.out.println("no ha funcionao: "+ exception);
        }

        return "se ha super cagao";
    }

//    public String validateToken();

}
