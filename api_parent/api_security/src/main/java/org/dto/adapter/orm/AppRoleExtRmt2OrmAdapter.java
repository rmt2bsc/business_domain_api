package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dto.DefaultCategoryAdapter;

/**
 * Adapts an RMT2 ORM <i>VwAppRoles</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 * 
 */
class AppRoleExtRmt2OrmAdapter extends DefaultCategoryAdapter {

    private VwAppRoles ar;

    private String userName;

    private int userLoginId;

    private AppRoleExtRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a AppRoleExtRmt2OrmAdapter using an instance of <i>VwAppRoles</i>
     * and the user's login id.
     * 
     * @param appRole
     *            an instance of {@link VwAppRoles}
     * @param userName
     *            the user's login id
     */
    protected AppRoleExtRmt2OrmAdapter(VwAppRoles appRole, String userName) {
        this();
        if (appRole == null) {
            appRole = new VwAppRoles();
        }
        this.ar = appRole;
        this.setUpdateUserId(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleId(int)
     */
    @Override
    public void setAppRoleId(int value) {
        this.ar.setAppRoleId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleId()
     */
    @Override
    public int getAppRoleId() {
        return this.ar.getAppRoleId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleCode(java.lang.String)
     */
    @Override
    public void setAppRoleCode(String value) {
        this.ar.setAppRoleCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleCode()
     */
    @Override
    public String getAppRoleCode() {
        return this.ar.getAppRoleCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleName(java.lang.String)
     */
    @Override
    public void setAppRoleName(String value) {
        this.ar.setAppRoleName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppName()
     */
    @Override
    public String getAppRoleName() {
        return this.ar.getAppRoleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleDescription(java.lang
     * .String)
     */
    @Override
    public void setAppRoleDescription(String value) {
        this.ar.setAppRoleDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleDescription()
     */
    @Override
    public String getAppRoleDescription() {
        return this.ar.getAppRoleDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleId(int)
     */
    @Override
    public void setRoleId(int value) {
        this.ar.setRoleId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleId()
     */
    @Override
    public int getRoleId() {
        return this.ar.getRoleId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setApplicationId(int)
     */
    @Override
    public void setApplicationId(int value) {
        this.ar.setApplicationId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getApplicationId()
     */
    @Override
    public int getApplicationId() {
        return this.ar.getApplicationId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleName(java.lang.String)
     */
    @Override
    public void setRoleName(String value) {
        this.ar.setRoleName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleName()
     */
    @Override
    public String getRoleName() {
        return this.ar.getRoleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
        this.ar.setAppName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppName()
     */
    @Override
    public String getAppName() {
        return this.ar.getAppName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
        this.userName = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getUsername()
     */
    @Override
    public String getUsername() {
        return this.userName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        this.userLoginId = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return this.userLoginId;
    }

}
