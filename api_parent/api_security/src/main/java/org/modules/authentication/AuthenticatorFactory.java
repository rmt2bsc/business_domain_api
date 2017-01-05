package org.modules.authentication;

import com.RMT2Base;

/**
 * Factory for creating {@link Authenticator} objects.
 * 
 * @author rterrell
 * 
 */
public class AuthenticatorFactory extends RMT2Base {

    /**
     * Create an instance of Authenticator using a user authenticator
     * implementation.
     * 
     * @return an instance of {@link Authenticator}
     */
    public final Authenticator createAuthenticator() {
        Authenticator a = new UserAuthenticatorImpl();
        return a;
    }

}
