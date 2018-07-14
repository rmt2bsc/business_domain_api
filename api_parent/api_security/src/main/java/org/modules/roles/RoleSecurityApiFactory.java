package org.modules.roles;

import org.modules.SecurityConstants;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating api instances that manage the Role module.
 * 
 * @author rterrell
 * 
 */
public class RoleSecurityApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public RoleSecurityApiFactory() {
        return;
    }

    /**
     * Creates an instance of AppApi using a role api implementation.
     * 
     * @return an instance of {@link RoleApi}
     */
    public static final RoleApi createRoleApi() {
        return createRoleApi(SecurityConstants.APP_NAME);
    }

    /**
     * Creates an instance of AppApi using a role api implementation.
     *
     * @param appName
     * @return an instance of {@link RoleApi}
     */
    public static final RoleApi createRoleApi(String appName) {
        RoleApi api = new RoleApiImpl(appName);
        return api;
    }
    
    /**
     * Creates an instance of AppRoleApi using a application role
     * implementation.
     * 
     * @return an instance of {@link AppRoleApi}
     */
    public static final AppRoleApi createAppRoleApi() {
        return createAppRoleApi(SecurityConstants.APP_NAME);
    }

    /**
     * Creates an instance of AppRoleApi using a application role
     * implementation.
     * 
     * @param appName
     * @return an instance of {@link AppRoleApi}
     */
    public static final AppRoleApi createAppRoleApi(String appName) {
        AppRoleApi api = new AppRoleApiImpl(appName);
        return api;
    }
    
    /**
     * Creates an instance of UserAppRoleApi using an user application role
     * implementation.
     * 
     * @return an instance of {@link UserAppRoleApi}
     */
    public static final UserAppRoleApi createUserAppRoleApi() {
        return createUserAppRoleApi(SecurityConstants.APP_NAME);
    }
    
    /**
     * Creates an instance of UserAppRoleApi using an user application role
     * implementation.
     * 
     * @param appName
     * @return an instance of {@link UserAppRoleApi}
     */
    public static final UserAppRoleApi createUserAppRoleApi(String appName) {
        UserAppRoleApi api = new UserAppRoleApiImpl(appName);
        return api;
    }
    
    /**
     * Creates an instance of UserAppRoleApi using an user application role
     * implementation.
     * 
     * @param dao
     *            an instance of {@link DaoClient}
     * @return an instance of {@link UserAppRoleApi}
     */
    public static final UserAppRoleApi createUserAppRoleApi(DaoClient dao) {
        UserAppRoleApi api = new UserAppRoleApiImpl(dao);
        return api;
    }
}
