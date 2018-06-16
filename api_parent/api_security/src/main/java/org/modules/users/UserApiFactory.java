package org.modules.users;

import org.modules.SecurityConstants;

import com.RMT2Base;

/**
 * A factory for creating api instances that manage the User and Group module.
 * 
 * @author rterrell
 * 
 */
public class UserApiFactory extends RMT2Base {

    
    
    /**
     * Default method.
     */
    public UserApiFactory() {
        return;
    }

    /**
     * Creates an instance of UserApi using the user/group api implementation.
     * 
     * @return an instance of {@link UserApi}
     */
    public static final UserApi createApiInstance() {
        UserApi api = createApiInstance(SecurityConstants.APP_NAME);
        return api;
        
    }
    
    /**
     * Create a UserApi using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    public static final UserApi createApiInstance(String appName) {
        UserApi api = new UserApiImpl(appName);
        return api;
    }
}
