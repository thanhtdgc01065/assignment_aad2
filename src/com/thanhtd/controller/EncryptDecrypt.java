/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import com.thanhtd.view.Main;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author trduy
 */
public class EncryptDecrypt {
    public EncryptDecrypt() {
    }

    public static String EncryptString(String text) {
        return EncryptString(text, Main.AES_MASTER_PASSWORD_KEY);
    }

    public static String EncryptString(String text, String keyPass) {
        try {
            Key aesKey = new SecretKeySpec(keyPass.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            String encryptString = Hex.encodeHexString(encrypted);
            return encryptString;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            //Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error when Encrypt data.");
        }
        return null;
    }

    public static String DecryptString(String text) {
        return DecryptString(text, Main.AES_MASTER_PASSWORD_KEY);
    }

    public static String DecryptString(String text, String keyPass) {
        try {
            Key aesKey = new SecretKeySpec(keyPass.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypt = Hex.decodeHex(text.toCharArray());
            String decrypted = new String(cipher.doFinal(decrypt));
            return decrypted;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | DecoderException | IllegalBlockSizeException | BadPaddingException ex) {
            //Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error when Decrypt data.");
        }
        return null;
    }
}
