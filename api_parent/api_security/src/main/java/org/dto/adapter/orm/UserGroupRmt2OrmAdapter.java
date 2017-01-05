package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwUserGroup;
import org.dto.DefaultUserAdapter;

/**
 * Adapts an RMT2 ORM <i>VwUserGroup</i> object to an <i>UserDto</i> and an
 * <i>User</i>.
 * 
 * @author rterrell
 * 
 */
class UserGroupRmt2OrmAdapter extends DefaultUserAdapter {

    private VwUserGroup u;

    private UserGroupRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a UserGroupRmt2OrmAdapter using an instance of <i>VwUserGroup</i>.
     * 
     * @param userLogin
     *            an instance of {@link VwUserGroup}
     */
    public UserGroupRmt2OrmAdapter(VwUserGroup userLogin) {
        this();
        this.u = userLogin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getUid()
     */
    @Override
    public int getUid() {
        return u.getLoginId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setUid(int)
     */
    @Override
    public void setUid(int uid) {
        this.u.setLoginId(uid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getLoginId()
     */
    @Override
    public String getLoginId() {
        return this.u.getUsername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setLoginId(java.lang.String)
     */
    @Override
    public void setLoginId(String loginId) {
        this.u.setUsername(loginId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getDescription()
     */
    @Override
    public String getUserDescription() {
        return this.u.getUserDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setDescription(java.lang.String)
     */
    @Override
    public void setUserDescription(String description) {
        this.u.setUserDescription(description);
    }

    /**
     * @return the active
     */
    public int getActive() {
        return this.u.getActive();
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(int active) {
        this.u.setActive(active);
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(String firstname) {
        this.u.setFirstname(firstname);
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return this.u.getLastname();
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.u.setLastname(lastname);
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return this.u.getBirthDate();
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.u.setBirthDate(birthDate);
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return this.u.getSsn();
    }

    /**
     * @param ssn
     *            the ssn to set
     */
    public void setSsn(String ssn) {
        this.u.setSsn(ssn);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.u.getEmail();
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.u.setEmail(email);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setStartDate(java.util.Date)
     */
    @Override
    public void setStartDate(Date value) {
        this.u.setStartDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getStartDate()
     */
    @Override
    public Date getStartDate() {
        return this.u.getStartDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setTerminationDate(java.util.Date)
     */
    @Override
    public void setTerminationDate(Date value) {
        this.u.setTerminationDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getTerminationDate()
     */
    @Override
    public Date getTerminationDate() {
        return this.u.getTerminationDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupId()
     */
    @Override
    public int getGroupId() {
        return this.u.getGroupId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
        this.u.setGroupId(grpId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupDescription()
     */
    @Override
    public String getGrp() {
        return this.u.getGroupDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.DefaultUserRmt2OrmAdapter#setGroupDescription(java.lang.String)
     */
    @Override
    public void setGrp(String value) {
        this.u.setGroupDescription(value);
    }
}
