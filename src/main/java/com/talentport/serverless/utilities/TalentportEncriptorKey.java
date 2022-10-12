package com.talentport.serverless.utilities; /**
 * Created by rafaelmendezsotelo on 15/06/17.
 */


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public final class TalentportEncriptorKey {

	 private static String traderMainKey = "T4ln3ncryPtEd.K3Y.T4ln3ncryPtEd+";


	    private static String passwordKey = "T4ln3ncryPtEd.K3Y.T4ln3ncryPtEd+";

    public final static String encode(String txt) {
        try {
            byte[] clientKeyBytes = traderMainKey.getBytes();
            SecretKeySpec clientKey = new SecretKeySpec(clientKeyBytes, 0, clientKeyBytes.length, "AES");
            Cipher encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encCipher.init(Cipher.ENCRYPT_MODE, clientKey);
            byte[] ivBytes = encCipher.getIV();
            byte[] dataBytes = encCipher.doFinal(txt.getBytes(StandardCharsets.UTF_8));
            byte[] concat = new byte[ivBytes.length + dataBytes.length];
            System.arraycopy(ivBytes, 0, concat, 0, ivBytes.length);
            System.arraycopy(dataBytes, 0, concat, ivBytes.length, dataBytes.length);
            return DatatypeConverter.printBase64Binary(concat);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String decode(String txt) {
        byte[] dataBytes = DatatypeConverter.parseBase64Binary(txt);
        byte[] IV = Arrays.copyOfRange(dataBytes, 0, 16);
        byte[] data = Arrays.copyOfRange(dataBytes, 16, dataBytes.length);
        byte[] clientKeyBytes = traderMainKey.getBytes();
        SecretKeySpec clientKey = new SecretKeySpec(clientKeyBytes, 0, clientKeyBytes.length, "AES");
        Cipher decCipher = null;
        try {
            decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decCipher.init(Cipher.DECRYPT_MODE, clientKey, new IvParameterSpec(IV));
            return new String(decCipher.doFinal(data), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String encodePwd(String pwd) {
        try {
            byte[] clientKeyBytes = passwordKey.getBytes();
            SecretKeySpec clientKey = new SecretKeySpec(clientKeyBytes, 0, clientKeyBytes.length, "AES");
            Cipher pwdCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            pwdCipher.init(Cipher.ENCRYPT_MODE, clientKey);
            byte[] dataBytes = pwdCipher.doFinal(pwd.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printBase64Binary(dataBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String decodePwd(String pwd) {
        byte[] dataBytes = DatatypeConverter.parseBase64Binary(pwd);
        byte[] clientKeyBytes = passwordKey.getBytes();
        SecretKeySpec clientKey = new SecretKeySpec(clientKeyBytes, 0, clientKeyBytes.length, "AES");
        Cipher pwdCipher = null;
        try {
            pwdCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            pwdCipher.init(Cipher.DECRYPT_MODE, clientKey);
            return new String(pwdCipher.doFinal(dataBytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
