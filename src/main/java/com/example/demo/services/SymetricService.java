package com.example.demo.services;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

@Service
public class SymetricService {

    public SecretKey decodeSecretKey(String secretKey){//this method use to decode secret key
        byte[] decodeKey = getDecoder().decode(secretKey);
        SecretKey secretKey1= new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
        return secretKey1;
    }
    public IvParameterSpec decodeIVParameterSpec(String ivParameterString){//this function use to decode salt or IVParameterSpec
        byte [] decodedIV = Base64.getDecoder().decode(ivParameterString);
        return  new IvParameterSpec(decodedIV);
    }
//this method use to decrypt message and change message from chipertext into plaintext
    public String decrypt(String cipherText, SecretKey secretKey, String algorithm, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] raw = getDecoder().decode(cipherText);
        byte[] stringBytes= cipher.doFinal(raw);
        String stringPlainText= new String(stringBytes, "UTF-8");
        return stringPlainText;
    }
}
