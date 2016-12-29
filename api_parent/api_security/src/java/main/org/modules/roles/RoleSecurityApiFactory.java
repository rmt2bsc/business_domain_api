package org.modules.roles;

import com.RMT2Base;

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
    public RoleApi createRoleApi() {
        RoleApi api = new RoleApiImpl();
        return api;
    }

    /**
     * Creates an instance of AppRoleApi using a application role
     * implementation.
     * 
     * @return an instance of {@link AppRoleApi}
     */
    public AppRoleApi createAppRoleApi() {
        AppRoleApi api = new AppRoleApiImpl();
        return api;
    }

    /**
     * Creates an instance of UserAppRoleApi using an user application role
     * implementation.
     * 
     * @return an instance of {@link UserAppRoleApi}
     */
    public UserAppRoleApi createUserAppRoleApi() {
        UserAppRoleApi api = new UserAppRoleApiImpl();
        return api;
    }
}
