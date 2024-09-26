package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwUser;
import org.dto.DefaultUserAdapter;

/**
 * Adapts an RMT2 ORM <i>VwUser</i> object to an <i>UserDto</i> and an
 * <i>User</i>.
 * 
 * @author rterrell
 * 
 */
class UserExtRmt2OrmAdapter extends DefaultUserAdapter {

    private VwUser u;

    private UserExtRmt2OrmAdapter() {
        super();
        return;
    }

    /**
     * Create a UserExtRmt2OrmAdapter using an instance of <i>VwUser</i>.
     * 
     * @param userLogin
     *            an instance of {@link VwUser}
     */
    public UserExtRmt2OrmAdapter(VwUser userLogin) {
        this();
        this.u = userLogin;
        this.setDateCreated(userLogin.getDateCreated());
        this.setDateUpdated(userLogin.getDateUpdated());
        this.setUpdateUserId(userLogin.getUserId());
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
        return this.u.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setDescription(java.lang.String)
     */
    @Override
    public void setUserDescription(String description) {
        this.u.setDescription(description);
    }

    /**
     * @return the totalLogons
     */
    public int getTotalLogons() {
        return this.u.getTotalLogons();
    }

    /**
     * @param totalLogons
     *            the totalLogons to set
     */
    public void setTotalLogons(int totalLogons) {
        this.u.setTotalLogons(totalLogons);
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
     * @return the firstname
     */
    public String getFirstname() {
        return this.u.getFirstname();
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
     * @see org.dto.DefaultUserRmt2OrmAdapter#isLoggedIn()
     */
    @Override
    public int getLoggedIn() {
        return this.u.getLoggedIn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoggedIn(boolean)
     */
    @Override
    public void setLoggedIn(int flag) {
        this.u.setLoggedIn(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        this.u.setLoginId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return u.getLoginId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
        this.u.setUsername(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getUsername()
     */
    @Override
    public String getUsername() {
        return this.u.getUsername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupId()
     */
    @Override
    public int getGroupId() {
        return this.u.getGrpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
        this.u.setGrpId(grpId);
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

    @Override
    public String getPassword() {
       return this.u.getPassword();
    }

    @Override
    public void setPassword(String password) {
        this.u.setPassword(password);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserAdapter#getGrpDescription()
     */
    @Override
    public String getGrpDescription() {
        return this.u.getGrpName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserAdapter#setGrpDescription(java.lang.String)
     */
    @Override
    public void setGrpDescription(String value) {
        this.u.setGrpName(value);
    }

}
