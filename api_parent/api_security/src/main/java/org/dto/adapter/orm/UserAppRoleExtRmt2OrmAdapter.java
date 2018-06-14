package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dto.DefaultCategoryAdapter;

/**
 * Adapts an RMT2 ORM <i>VwUserAppRoles</i> object to an <i>CategoryDto</i>.
 * 
 * @author rterrell
 * 
 */
class UserAppRoleExtRmt2OrmAdapter extends DefaultCategoryAdapter {

    private VwUserAppRoles uar;

    private UserAppRoleExtRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a UserAppRoleExtRmt2OrmAdapter initialized with an instance of
     * {@link VwUserAppRoles} and the identity of the user accessing this
     * adapter.
     * 
     * @param userAppRole
     *            an instance of {@link VwUserAppRoles}
     * @param updateUserId
     *            the login id of the user accessing this adapter.
     */
    public UserAppRoleExtRmt2OrmAdapter(VwUserAppRoles userAppRole, String updateUserId) {
        this();
        if (userAppRole == null) {
            userAppRole = new VwUserAppRoles();
        }
        this.uar = userAppRole;
        this.setUpdateUserId(updateUserId);
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        this.uar.setLoginUid(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return this.uar.getLoginUid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
        this.uar.setUsername(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getUsername()
     */
    @Override
    public String getUsername() {
        return this.uar.getUsername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.uar.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getActive()
     */
    @Override
    public int getActive() {
        return this.uar.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setFirstname(java.lang.String)
     */
    @Override
    public void setFirstname(String value) {
        this.uar.setFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getFirstname()
     */
    @Override
    public String getFirstname() {
        return this.uar.getFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setLastname(java.lang.String)
     */
    @Override
    public void setLastname(String value) {
        this.uar.setLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getLastname()
     */
    @Override
    public String getLastname() {
        return this.uar.getLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setBirthDate(java.util.Date)
     */
    @Override
    public void setBirthDate(Date value) {
        this.uar.setBirthDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getBirthDate()
     */
    @Override
    public Date getBirthDate() {
        return this.uar.getBirthDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setSsn(java.lang.String)
     */
    @Override
    public void setSsn(String value) {
        this.uar.setSsn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getSsn()
     */
    @Override
    public String getSsn() {
        return this.uar.getSsn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setUserDescription(java.lang.String
     * )
     */
    @Override
    public void setUserDescription(String value) {
        this.uar.setUserDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getUserDescription()
     */
    @Override
    public String getUserDescription() {
        return this.uar.getUserDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setStartDate(java.util.Date)
     */
    @Override
    public void setStartDate(Date value) {
        this.uar.setStartDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getStartDate()
     */
    @Override
    public Date getStartDate() {
        return this.uar.getStartDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setTerminationDate(java.util.Date)
     */
    @Override
    public void setTerminationDate(Date value) {
        this.uar.setTerminationDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getTerminationDate()
     */
    @Override
    public Date getTerminationDate() {
        return this.uar.getTerminationDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setEmail(java.lang.String)
     */
    @Override
    public void setEmail(String value) {
        this.uar.setEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getEmail()
     */
    @Override
    public String getEmail() {
        return this.uar.getEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int value) {
        this.uar.setGroupId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getGroupId()
     */
    @Override
    public int getGroupId() {
        return this.uar.getGroupId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setGroupDescription(java.lang.String
     * )
     */
    @Override
    public void setGrp(String value) {
        this.uar.setGroupDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getGroupDescription()
     */
    @Override
    public String getGrp() {
        return this.uar.getGroupDescription();
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
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleCode(java.lang.String)
     */
    @Override
    public void setAppRoleCode(String value) {
        this.uar.setAppRoleCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleCode()
     */
    @Override
    public String getAppRoleCode() {
        return this.uar.getAppRoleCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultCategoryRmt2OrmAdapter#setAppRoleName(java.lang.String)
     */
    @Override
    public void setAppRoleName(String value) {
        this.uar.setAppRoleName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleName()
     */
    @Override
    public String getAppRoleName() {
        return this.uar.getAppRoleName();
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
        this.uar.setAppRoleDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppRoleDescription()
     */
    @Override
    public String getAppRoleDescription() {
        return this.uar.getAppRoleDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleId(int)
     */
    @Override
    public void setRoleId(int value) {
        this.uar.setRoleId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleId()
     */
    @Override
    public int getRoleId() {
        return this.uar.getRoleId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setRoleName(java.lang.String)
     */
    @Override
    public void setRoleName(String value) {
        this.uar.setRoleName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getRoleName()
     */
    @Override
    public String getRoleName() {
        return this.uar.getRoleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setApplicationId(int)
     */
    @Override
    public void setApplicationId(int value) {
        this.uar.setApplicationId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getApplicationId()
     */
    @Override
    public int getApplicationId() {
        return this.uar.getApplicationId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#setAppName(java.lang.String)
     */
    @Override
    public void setAppName(String value) {
        this.uar.setAppName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultCategoryRmt2OrmAdapter#getAppName()
     */
    @Override
    public String getAppName() {
        return this.uar.getAppName();
    }

    @Override
    public String getGrpDescription() {
        return this.uar.getGroupDescription();
    }

    @Override
    public void setGrpDescription(String value) {
        this.uar.setGroupDescription(value);
    }

}
