package org.modules.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * @author roy.terrell
 *
 */
public class SecureRandomNumber {

    private static final String SHA1_PRNG = "SHA1PRNG";

    // Default here is 64-bits of random goodness.
    private static final int DEFAULT_RANDOM_SIZE = 64;

    private static final char HEX_DIGIT[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    // This isn't thread safe but we probably don't really care
    // since all we're doing is reading a bunch of random numbers
    // out of the generator.
    private static final SecureRandom sRandom__;

    static {
        try {
            sRandom__ = SecureRandom.getInstance(SHA1_PRNG);
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

    /**
     * Get the number of next random bits in this SecureRandom generators' sequence.
     * 
     * @param bits
     *            how many random bits you want
     * @return byte[]
     * @throws IllegalArgumentException
     *             if the arg isn't divisible by eight
     */
    public static byte[] getNextSecureRandom(int bits) {

        // Make sure the number of bits we're asking for is at least
        // divisible by 8.
        if ((bits % 8) != 0) {
            throw new IllegalArgumentException("Size is not divisible " + "by 8!");
        }

        // Usually 64-bits of randomness, 8 bytes
        final byte[] bytes = new byte[bits / 8];

        // Get the next 64 random bits. Forces SecureRandom
        // to seed itself before returning the bytes.
        sRandom__.nextBytes(bytes);

        return bytes;

    }

    /**
     * Convert a byte array into its hex String equivalent.
     * 
     * @param bytes
     * @return String
     */
    public static String toHex(byte[] bytes) {

        if (bytes == null) {
            return null;
        }

        StringBuilder buffer = new StringBuilder(bytes.length * 2);
        for (byte thisByte : bytes) {
            buffer.append(byteToHex(thisByte));
        }

        return buffer.toString();

    }

    /**
     * Convert a single byte into its hex String equivalent.
     * 
     * @param b
     * @return String
     */
    private static String byteToHex(byte b) {
        char[] array = { HEX_DIGIT[(b >> 4) & 0x0f], HEX_DIGIT[b & 0x0f] };
        return new String(array);
        /**
         * @return
         */
    }

    /**
     * Method getRandomPassword. Generate a random password
     * 
     * @return String
     */
    public static String getRandomPassword() {

        // Get 64-bits of secure random goodness.
        final byte[] randBytes = SecureRandomNumber.getNextSecureRandom(DEFAULT_RANDOM_SIZE);

        // Convert it to something pretty.
        String hisAnswer = new String(SecureRandomNumber.toHex(randBytes));
        StringBuilder answer = new StringBuilder();
        for (int j = 0; j < hisAnswer.length(); j++) {
            double rand = Math.random();
            String a = hisAnswer.substring(j, j + 1);
            if (rand < 0.5) { // Capitalize some letters
                a = a.toUpperCase();
            }
            answer.append(a);
        }
        return answer.toString();
    }

}