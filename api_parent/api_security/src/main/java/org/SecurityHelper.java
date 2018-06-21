package org;

import com.RMT2Base;
import com.api.config.ConfigConstants;
import com.api.util.RMT2Base64Decoder;
import com.api.util.RMT2Base64Encoder;

/**
 * Provides utility operations regarding security.
 * 
 * @author rterrell
 * 
 */
public class SecurityHelper extends RMT2Base {

    private static Integer encryptCycles;

    /**
     * Creates a SecurityHelper object.
     */
    public SecurityHelper() {
        if (SecurityHelper.encryptCycles == null) {
            String temp = System
                    .getProperty(ConfigConstants.PROPNAME_ENCRYPT_CYCLE);
            try {
                SecurityHelper.encryptCycles = new Integer(temp);
            } catch (NumberFormatException e) {
                SecurityHelper.encryptCycles = 10;
            }
        }
    }

    /**
     * Creates an encrypted password using _pw as the source.
     * 
     * @param password
     *            The password to encrypt.
     * @return The encrypted password.
     */
    public String encryptPassword(String password) {

        String pwResult;
        pwResult = password;
        for (int ndx = 0; ndx < SecurityHelper.encryptCycles; ndx++) {
            pwResult = RMT2Base64Encoder.encode(pwResult);
        }
        return pwResult;
    }

    /**
     * Decodes an encrypted password using _pw as the source.
     * 
     * @param password
     *            The password to decrypt.
     * @return The decrypted password.
     */
    public String decryptPassword(String password) {

        String pwResult;
        pwResult = password;
        for (int ndx = 0; ndx < SecurityHelper.encryptCycles; ndx++) {
            pwResult = RMT2Base64Decoder.decode(pwResult);
        }
        return pwResult;
    }

}
