package org.modules.authentication;

import org.modules.SecurityConstants;

import com.RMT2Base;
import com.api.persistence.DaoClient;

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
    public static final Authenticator createApi() {
        return createApi(SecurityConstants.APP_NAME);
    }

    /**
     * Create an instance of Authenticator using a user authenticator
     * implementation.
     * 
     * @param appName
     * @return
     */
    public static final Authenticator createApi(String appName) {
        Authenticator a = new UserAuthenticatorImpl(appName);
        return a;
    }
    
    /**
     * Create an instance of Authenticator using a user authenticator
     * implementation.
     * 
     * @param dao
     * @return
     */
    public static final Authenticator createApi(DaoClient dao) {
        Authenticator a = new UserAuthenticatorImpl(dao);
        return a;
    }
}
