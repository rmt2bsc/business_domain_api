package org.dto.adapter.ldap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dao.mapping.orm.ldap.LdapUser;
import org.dto.DefaultUserAdapter;

import com.api.util.RMT2Date;

/**
 * Adapts a LDAP <i>LdapUser</i> object to an <i>UserDto</i> and an <i>User</i>.
 * 
 * @author rterrell
 * 
 */
class UserLdapAdapter extends DefaultUserAdapter {

    private LdapUser u;

    private UserLdapAdapter() {
        super();
        return;
    }

    /**
     * Create a UserLdapAdapter using an instance of <i>LdapUser</i>.
     * 
     * @param user
     *            an instance of {@link LdapUser}
     */
    public UserLdapAdapter(LdapUser user) {
        this();
        if (user == null) {
            user = new LdapUser();
        }
        this.u = user;

        // Set up user's granted application roles.
        if (user.getAr() != null && user.getAr().size() > 0) {
            this.roles = new ArrayList<String>();
            for (String role : user.getAr()) {
                this.roles.add(role);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getLoginId()
     */
    @Override
    public String getLoginId() {
        return this.u.getLoginid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setLoginId(java.lang.String)
     */
    @Override
    public void setLoginId(String loginId) {
        this.u.setLoginid(loginId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#getDescription()
     */
    @Override
    public String getUserDescription() {
        if (this.u.getDescription() != null
                && this.u.getDescription().size() > 0) {
            return this.u.getDescription().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.entity.User#setDescription(java.lang.String)
     */
    @Override
    public void setUserDescription(String description) {
        this.u.setDescription(new ArrayList<String>());
        this.u.getDescription().add(description);
    }

    /**
     * This method is not supported
     * 
     * @throws UnsupportedOperationException
     */
    public String getPassword() {
        return this.u.getUserPassword();
    }

    /**
     * This method is not supported
     * 
     * @throws UnsupportedOperationException
     */
    public void setPassword(String password) {
        this.u.setUserPassword(password);
    }

    /**
     * @return the totalLogons
     */
    public int getTotalLogons() {
        int loginCount;
        try {
            loginCount = Integer.parseInt(this.u.getLc());
        } catch (NumberFormatException e) {
            loginCount = 0;
        }
        return loginCount;
    }

    /**
     * @param totalLogons
     *            the totalLogons to set
     */
    public void setTotalLogons(int totalLogons) {
        this.u.setLc(String.valueOf(totalLogons));
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return this.u.getFn();
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(String firstname) {
        this.u.setFn(firstname);
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        if (this.u.getSn() != null && this.u.getSn().size() > 0) {
            return this.u.getSn().get(0);
        }
        return null;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.u.setSn(new ArrayList<String>());
        this.u.getSn().add(lastname);
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        Date dt;
        try {
            dt = RMT2Date.stringToDate(this.u.getDob());
        } catch (Exception e) {
            dt = null;
        }
        return dt;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        String dt;
        try {
            dt = RMT2Date.formatDate(birthDate, "MM/dd/yyyy");
        } catch (Exception e) {
            dt = null;
        }
        this.u.setDob(dt);
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
        int loggedIn;
        try {
            loggedIn = Integer.parseInt(this.u.getLoggedIn());
        } catch (NumberFormatException e) {
            loggedIn = 0;
        }
        return loggedIn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setLoggedIn(boolean)
     */
    @Override
    public void setLoggedIn(int flag) {
        this.u.setLoggedIn(String.valueOf(flag));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {
        this.u.setLoginid(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.DefaultUserRmt2OrmAdapter#getUsername()
     */
    @Override
    public String getUsername() {
        return this.u.getLoginid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getGroupDescription()
     */
    @Override
    public String getGrp() {
        return this.u.getGrp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.ldap.DefaultUserLdapAdapter#setGroupDescription(java.lang.String)
     */
    @Override
    public void setGrp(String value) {
        this.u.setGrp(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getTitleName()
     */
    @Override
    public String getTitleName() {
        return this.u.getPt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setTitleName(java.lang.String)
     */
    @Override
    public void setTitleName(String value) {
        this.u.setPt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setStartDate(java.util.Date)
     */
    @Override
    public void setStartDate(Date value) {
        String dt;
        try {
            dt = RMT2Date.formatDate(value, "MM/dd/yyyy");
        } catch (Exception e) {
            dt = null;
        }
        this.u.setStartDate(dt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getStartDate()
     */
    @Override
    public Date getStartDate() {
        Date dt;
        try {
            dt = RMT2Date.stringToDate(this.u.getStartDate());
        } catch (Exception e) {
            dt = null;
        }
        return dt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.ldap.DefaultUserLdapAdapter#setTerminationDate(java.util.Date)
     */
    @Override
    public void setTerminationDate(Date value) {
        String dt;
        try {
            dt = RMT2Date.formatDate(value, "MM/dd/yyyy");
        } catch (Exception e) {
            dt = null;
        }
        this.u.setTermDate(dt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getTerminationDate()
     */
    @Override
    public Date getTerminationDate() {
        Date dt;
        try {
            dt = RMT2Date.stringToDate(this.u.getTermDate());
        } catch (Exception e) {
            dt = null;
        }
        return dt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getAddress()
     */
    @Override
    public String getAddress() {
        return this.u.getAddr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setAddress(java.lang.String)
     */
    @Override
    public void setAddress(String value) {
        this.u.setAddr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getCity()
     */
    @Override
    public String getCity() {
        return this.u.getCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.u.setCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getState()
     */
    @Override
    public String getState() {
        return this.u.getState();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        this.u.setState(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getZip()
     */
    @Override
    public String getZip() {
        return this.u.getZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setZip(java.lang.String)
     */
    @Override
    public void setZip(String value) {
        this.u.setZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getCountry()
     */
    @Override
    public String getCountry() {
        return this.u.getCountry();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setCountry(java.lang.String)
     */
    @Override
    public void setCountry(String value) {
        this.u.setCountry(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getHomePhone()
     */
    @Override
    public String getHomePhone() {
        return this.u.getPhoneHome();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setHomePhone(java.lang.String)
     */
    @Override
    public void setHomePhone(String value) {
        this.u.setPhoneHome(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getOfficePhone()
     */
    @Override
    public String getOfficePhone() {
        return this.u.getPhoneOffice();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setOfficePhone(java.lang.String)
     */
    @Override
    public void setOfficePhone(String value) {
        this.u.setPhoneOffice(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getMobilePhone()
     */
    @Override
    public String getMobilePhone() {
        return this.u.getPhoneMobile();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setMobilePhone(java.lang.String)
     */
    @Override
    public void setMobilePhone(String value) {
        this.u.setPhoneMobile(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getFax()
     */
    @Override
    public String getFax() {
        return this.u.getPhoneFax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setFax(java.lang.String)
     */
    @Override
    public void setFax(String value) {
        this.u.setPhoneFax(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getOtherPhone()
     */
    @Override
    public String getOtherPhone() {
        return this.u.getPhoneOther();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setOtherPhone(java.lang.String)
     */
    @Override
    public void setOtherPhone(String value) {
        this.u.setPhoneOther(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getPager()
     */
    @Override
    public String getPager() {
        return this.u.getPhonePager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setPager(java.lang.String)
     */
    @Override
    public void setPager(String value) {
        this.u.setPhonePager(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getShortname()
     */
    @Override
    public String getShortname() {
        if (this.u.getCn() != null && this.u.getCn().size() > 0) {
            return this.u.getCn().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setShortname(java.lang.String)
     */
    @Override
    public void setShortname(String shortname) {
        this.u.setCn(new ArrayList<String>());
        this.u.getCn().add(shortname);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getJobTitleDescription()
     */
    @Override
    public String getJobTitleDescription() {
        return this.u.getJt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setJobTitleDescription(java.lang.String)
     */
    @Override
    public void setJobTitleDescription(String value) {
        this.u.setJt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#getRoles()
     */
    @Override
    public List<String> getRoles() {
        return this.u.getAr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.ldap.DefaultUserLdapAdapter#setRoles(java.util.List)
     */
    @Override
    public void setRoles(List<String> roles) {
        this.u.setAr(roles);
    }
}
