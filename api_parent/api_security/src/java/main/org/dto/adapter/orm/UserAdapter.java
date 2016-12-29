package org.dto.adapter.orm;

import java.util.Date;

import org.SecurityHelper;
import org.dto.DefaultUserAdapter;
import org.dto.UserDto;

/**
 * Adapts an <i>UserDto</i> object to an <i>User</i>.
 * 
 * @author rterrell
 * 
 */
class UserAdapter extends DefaultUserAdapter {

    private UserDto u;

    private UserAdapter() {
        super();
        return;
    }

    /**
     * Create a UserAdapter using an instance of <i>UserDto</i>.
     * 
     * @param user
     *            an instance of {@link UserDto}
     */
    public UserAdapter(UserDto user) {
        this();
        this.u = user;
        this.setDateCreated(user.getDateCreated());
        this.setDateUpdated(user.getDateUpdated());
        this.setUpdateUserId(user.getUpdateUserId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getUid()
     */
    @Override
    public int getUid() {
        return u.getLoginUid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setUid(int)
     */
    @Override
    public void setUid(int uid) {
        this.u.setLoginUid(uid);
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
     * @return the password
     */
    public String getPassword() {
        // Decrypt password
        String inputPassword = this.u.getPassword();
        SecurityHelper helper = new SecurityHelper();
        String pw = null;
        try {
            pw = helper.decryptPassword(inputPassword);
        } catch (Exception e) {
            // password is already decrypted
            pw = inputPassword;
        }
        return pw;
    }

    /**
     * @param password
     *            the password to set
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
        return (this.u.getLoggedIn());
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
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        u.setLoginUid(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return u.getLoginUid();
    }

}
