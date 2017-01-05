package org.modules.users;

import com.RMT2Base;

/**
 * A factory for creating api instances that manage the User and Group module.
 * 
 * @author rterrell
 * 
 */
public class UserGroupMaintApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public UserGroupMaintApiFactory() {
        return;
    }

    /**
     * Creates an instance of UserApi using the user/group api implementation.
     * 
     * @return an instance of {@link UserApi}
     */
    public UserApi createApi() {
        UserApi api = new UserApiImpl();
        return api;
    }
}
