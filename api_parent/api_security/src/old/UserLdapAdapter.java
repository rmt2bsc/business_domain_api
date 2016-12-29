package org.dto.adapter.orm;

import java.util.Date;

import org.SecurityHelper;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dto.adapter.orm.DefaultUserRmt2OrmAdapter;

/**
 * Adapts an RMT2 ORM <i>UserLogin</i> object to an <i>UserDto</i> and an <i>User</i>.
 * 
 * @author rterrell
 *
 */
class UserLdapAdapter extends DefaultUserRmt2OrmAdapter {

    private UserLogin u;

    private UserLdapAdapter() {
	super();
	return;
    }

    /**
     * Create a UserRmt2OrmAdapter using an instance of <i>UserLogin</i>.
     * 
     * @param userLogin
     *          an instance of {@link UserLogin}
     */
    public UserLdapAdapter(UserLogin userLogin) {
	this();
	this.u = userLogin;
	this.setDateCreated(userLogin.getDateCreated());
	this.setDateUpdated(userLogin.getDateUpdated());
	this.setUpdateUserId(userLogin.getUserId());
    }

    /* (non-Javadoc)
     * @see org.entity.User#getUid()
     */
    @Override
    public int getUid() {
	return u.getLoginId();
    }

    /* (non-Javadoc)
     * @see org.entity.User#setUid(int)
     */
    @Override
    public void setUid(int uid) {
	this.u.setLoginId(uid);
    }

    /* (non-Javadoc)
     * @see org.entity.User#getLoginId()
     */
    @Override
    public String getLoginId() {
	return this.u.getUsername();
    }

    /* (non-Javadoc)
     * @see org.entity.User#setLoginId(java.lang.String)
     */
    @Override
    public void setLoginId(String loginId) {
	this.u.setUsername(loginId);
    }

    /* (non-Javadoc)
     * @see org.entity.User#getDescription()
     */
    @Override
    public String getDescription() {
	return this.u.getDescription();
    }

    /* (non-Javadoc)
     * @see org.entity.User#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String description) {
	this.u.setDescription(description);
    }

    /**
     * @return the password
     */
    public String getPassword() {
	// Decrypt password
	String inputPassword = this.u.getPassword();
	SecurityHelper helper = new SecurityHelper();
	String pw = null;
	try {
	    pw = helper.decryptPassword(inputPassword);    
	}
	catch (Exception e) {
	    // password is already decrypted
	    pw = inputPassword;
	}
	return pw;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.u.setPassword(password);
    }

    /**
     * @return the totalLogons
     */
    public int getTotalLogons() {
	return this.u.getTotalLogons();
    }

    /**
     * @param totalLogons the totalLogons to set
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
     * @param active the active to set
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
     * @param firstname the firstname to set
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
     * @param lastname the lastname to set
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
     * @param birthDate the birthDate to set
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
     * @param ssn the ssn to set
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
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.u.setEmail(email);
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#isLoggedIn()
     */
    @Override
    public int getLoggedIn() {
	return this.u.getLoggedIn();
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoggedIn(boolean)
     */
    @Override
    public void setLoggedIn(int flag) {
	this.u.setLoggedIn(flag);
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
	this.u.setUsername(value);
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#getUsername()
     */
    @Override
    public String getUsername() {
	return this.u.getUsername();
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#getGroupId()
     */
    @Override
    public int getGroupId() {
	return this.u.getGrpId();
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
	this.u.setGrpId(grpId);
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
	u.setLoginId(value);
    }

    /* (non-Javadoc)
     * @see org.dto.DefaultUserRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
	return u.getLoginId();
    }

}
