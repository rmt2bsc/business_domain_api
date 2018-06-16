package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dto.DefaultCategoryAdapter;

/**
 * Adapts an RMT2 ORM <i>UserAppRole</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 * 
 */
class UserAppRoleRmt2OrmAdapter extends DefaultCategoryAdapter {

    private UserAppRole uar;

    private UserAppRoleRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a UserAppRoleRmt2OrmAdapter initialized with an instance of
     * {@link UserAppRole} and the identity of the user accessing this
     * adapter.
     * 
     * @param userAppRole
     *            an instance of {@link UserAppRole}
     */
    public UserAppRoleRmt2OrmAdapter(UserAppRole userAppRole) {
        this();
        if (userAppRole == null) {
            userAppRole = new UserAppRole();
        }
        this.uar = userAppRole;
    }

    @Override
    public void setUserAppRoleId(int value) {
        this.uar.setUserAppRoleId(value);
    }

    @Override
    public int getUserAppRoleId() {
        return this.uar.getUserAppRoleId();
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleId(int)
     */
    @Override
    public void setAppRoleId(int value) {
        this.uar.setAppRoleId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleId()
     */
    @Override
    public int getAppRoleId() {
        return this.uar.getAppRoleId();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        this.uar.setLoginId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return this.uar.getLoginId();
    }

}
