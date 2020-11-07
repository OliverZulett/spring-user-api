package com.milankas.training.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.milankas.training.dto.user.UserOutputDTO;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    public String getToken(UserOutputDTO user) throws IOException {
        RSAPublicKey publicKey = (RSAPublicKey) this.readPublicKeyFromFile("./rsa/public.pem", "RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) this.readPrivateKeyFromFile("./rsa/private.pem", "RSA");
        Calendar jwtCreationTimeStamp = Calendar.getInstance();
        jwtCreationTimeStamp.setTime(new Date());
        Calendar jwtExpiredTimeStamp = Calendar.getInstance();
        jwtExpiredTimeStamp.setTime(new Date());
        jwtExpiredTimeStamp.add(Calendar.MINUTE, 30);
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withIssuer("user.api")
                    .withSubject(user.getUserId().toString())
                    .withJWTId(UUID.randomUUID().toString())
                    .withIssuedAt(jwtCreationTimeStamp.getTime())
                    .withExpiresAt(jwtExpiredTimeStamp.getTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            System.out.println("jwt generation failed: " + exception);
        }
        return null;
    }

    public DecodedJWT validateToken(String token) throws IOException {
        RSAPublicKey publicKey = (RSAPublicKey) this.readPublicKeyFromFile("./rsa/public.pem", "RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) this.readPrivateKeyFromFile("./rsa/private.pem", "RSA");
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println("jwt verify failed: " + exception);
        }
        return null;
    }

    private PublicKey readPublicKeyFromFile(String filepath, String algorithm) throws IOException {
        byte[] bytes = this.parsePEMFile(new File(filepath));
        return this.getPublicKey(bytes, algorithm);
    }

    private PrivateKey readPrivateKeyFromFile(String filepath, String algorithm) throws IOException {
        byte[] bytes = this.parsePEMFile(new File(filepath));
        return this.getPrivateKey(bytes, algorithm);
    }

    private byte[] parsePEMFile(File pemFile) throws IOException {
        if (!pemFile.isFile() || !pemFile.exists()) {
            throw new FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
        }
        PemReader reader = new PemReader(new FileReader(pemFile));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
    }

    private PublicKey getPublicKey(byte[] keyBytes, String algorithm) {
        PublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            publicKey = kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the public key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the public key");
        }
        return publicKey;
    }

    private PrivateKey getPrivateKey(byte[] keyBytes, String algorithm) {
        PrivateKey privateKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the private key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the private key");
        }
        return privateKey;
    }

}
