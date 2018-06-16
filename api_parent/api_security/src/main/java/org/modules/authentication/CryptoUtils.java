package org.modules.authentication;

import java.security.NoSuchAlgorithmException;

/**
 * Utility class for password creation
 */
public class CryptoUtils {

    /**
     * Private constructor
     */
    private CryptoUtils() {
        throw new IllegalAccessError("Static class. Do not instantiate");
    }

    /**
     * Method computeHash.
     * 
     * @param x
     *            String
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] computeHash(String x) throws NoSuchAlgorithmException {
        java.security.MessageDigest d = java.security.MessageDigest.getInstance("SHA-256");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    /**
     * Method byteArrayToHexString.
     * 
     * @param b
     *            byte[]
     * @return String
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * Method generatePassword.
     * 
     * @return String
     */
    public static String generatePassword() {
        return SecureRandomNumber.getRandomPassword();
    }

}