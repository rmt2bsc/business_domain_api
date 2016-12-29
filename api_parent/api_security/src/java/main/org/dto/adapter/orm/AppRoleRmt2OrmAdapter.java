package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dto.DefaultCategoryAdapter;

/**
 * Adapts an RMT2 ORM <i>AppRole</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 * 
 */
class AppRoleRmt2OrmAdapter extends DefaultCategoryAdapter {

    private AppRole ar;

    private AppRoleRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a AppRoleRmt2OrmAdapter using an instance of <i>AppRole</i>.
     * 
     * @param appRole
     *            an instance of {@link AppRole}
     */
    protected AppRoleRmt2OrmAdapter(AppRole appRole) {
        this();
        this.ar = appRole;
        this.setUpdateUserId(appRole.getUserId());
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
        this.ar.setCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleCode()
     */
    @Override
    public String getAppRoleCode() {
        return this.ar.getCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleName(java.lang.String)
     */
    @Override
    public void setAppRoleName(String value) {
        this.ar.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppName()
     */
    @Override
    public String getAppRoleName() {
        return this.ar.getName();
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
        this.ar.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleDescription()
     */
    @Override
    public String getAppRoleDescription() {
        return this.ar.getDescription();
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
        this.ar.setAppId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getApplicationId()
     */
    @Override
    public int getApplicationId() {
        return this.ar.getAppId();
    }

}
