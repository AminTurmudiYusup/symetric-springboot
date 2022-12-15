package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.services.SymetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SymetricService symetricService;

    @Value("${symetric.secret.key}")
    private String privateKey;


    @PostMapping("/user")//api for handle request for save user
    public Users addUser(@RequestHeader("salt")String salt, @RequestBody Users users) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String phoneNumber, identityNumber;

        SecretKey secretKey = symetricService.decodeSecretKey(this.privateKey);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec= symetricService.decodeIVParameterSpec(salt);
        //decrypt phone number and identity number before save to database
        phoneNumber = symetricService.decrypt(users.getPhone(), secretKey, algorithm, ivParameterSpec);
        identityNumber = symetricService.decrypt(users.getIdentityNumber(), secretKey, algorithm, ivParameterSpec);

        users.setPhone(phoneNumber);
        users.setIdentityNumber(identityNumber);

        return usersRepository.save(users);
    }
}
