package com.mycompany.petmanagermodelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginModelo {

    public boolean validarLogin(String usuario, char[] senhaChars) {
        String senhaCriptografada = encryptPassword(new String(senhaChars));

        String usuarioValido = "admin";
        String senhaCriptografadaValida = encryptPassword("123");

        // aq limpa array de caracteres ja q a string foi usada para criptografar
        java.util.Arrays.fill(senhaChars, ' ');

        return usuario.equals(usuarioValido) && senhaCriptografada.equals(senhaCriptografadaValida);
    }

    // Criptografia SHA-256 
    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}