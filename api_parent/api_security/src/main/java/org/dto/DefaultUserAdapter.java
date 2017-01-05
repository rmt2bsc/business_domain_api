package org.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.foundation.TransactionDtoImpl;
import com.api.security.User;

/**
 * The default adapter for user related objects which its method implementations
 * are simply stubbed.
 * <p>
 * 
 * @author rterrell
 * 
 */
public class DefaultUserAdapter extends TransactionDtoImpl implements User,
        UserDto {

    private static final long serialVersionUID = 5153167471548087421L;

    protected List<String> roles;

    protected int appCount;

    private boolean queryLoggedInFlag;

    /**
     * Create a DefaultUserAdapter
     */
    public DefaultUserAdapter() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setLoginUid(int)
     */
    @Override
    public void setLoginUid(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getLoginUid()
     */
    @Override
    public int getLoginUid() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String value) {

        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getUsername()
     */
    @Override
    public String getUsername() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getActive()
     */
    @Override
    public int getActive() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setFirstname(java.lang.String)
     */
    @Override
    public void setFirstname(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getFirstname()
     */
    @Override
    public String getFirstname() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setLastname(java.lang.String)
     */
    @Override
    public void setLastname(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getLastname()
     */
    @Override
    public String getLastname() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setBirthDate(java.util.Date)
     */
    @Override
    public void setBirthDate(Date value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getBirthDate()
     */
    @Override
    public Date getBirthDate() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setSsn(java.lang.String)
     */
    @Override
    public void setSsn(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getSsn()
     */
    @Override
    public String getSsn() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setStartDate(java.util.Date)
     */
    @Override
    public void setStartDate(Date value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getStartDate()
     */
    @Override
    public Date getStartDate() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setTerminationDate(java.util.Date)
     */
    @Override
    public void setTerminationDate(Date value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getTerminationDate()
     */
    @Override
    public Date getTerminationDate() {
        return null;
    }

    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new ArrayList<String>();
        }
        this.roles.add(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getAppCount()
     */
    @Override
    public int getAppCount() {
        return this.appCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setAppCount(int)
     */
    @Override
    public void setAppCount(int count) {
        this.appCount = count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#incrementAppCount()
     */
    @Override
    public void incrementAppCount() {
        this.appCount++;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#decrementAppCount()
     */
    @Override
    public void decrementAppCount() {
        if (this.appCount > 0) {
            this.appCount--;
        }
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }

    @Override
    public void setRoles(List<String> roles) {
        this.roles = roles;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getTotalLogons()
     */
    @Override
    public int getTotalLogons() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setTotalLogons(int)
     */
    @Override
    public void setTotalLogons(int totalLogons) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getUid()
     */
    @Override
    public int getUid() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setUid(int)
     */
    @Override
    public void setUid(int uid) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getLoginId()
     */
    @Override
    public String getLoginId() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setLoginId(java.lang.String)
     */
    @Override
    public void setLoginId(String loginId) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getDescription()
     */
    @Override
    public String getUserDescription() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setDescription(java.lang.String)
     */
    @Override
    public void setUserDescription(String description) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getPassword()
     */
    @Override
    public String getPassword() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setPassword(java.lang.String)
     */
    @Override
    public void setPassword(String password) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getPersonId()
     */
    @Override
    public int getPersonId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setPersonId(int)
     */
    @Override
    public void setPersonId(int personId) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getMidname()
     */
    @Override
    public String getMidname() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setMidname(java.lang.String)
     */
    @Override
    public void setMidname(String midname) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getMaidenname()
     */
    @Override
    public String getMaidenname() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setMaidenname(java.lang.String)
     */
    @Override
    public void setMaidenname(String maidenname) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getGeneration()
     */
    @Override
    public String getGeneration() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setGeneration(java.lang.String)
     */
    @Override
    public void setGeneration(String generation) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getShortname()
     */
    @Override
    public String getShortname() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setShortname(java.lang.String)
     */
    @Override
    public void setShortname(String shortname) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getTitle()
     */
    @Override
    public int getTitle() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setTitle(int)
     */
    @Override
    public void setTitle(int title) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getGenderId()
     */
    @Override
    public int getGenderId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setGenderId(int)
     */
    @Override
    public void setGenderId(int genderId) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getMaritalStatus()
     */
    @Override
    public int getMaritalStatus() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setMaritalStatus(int)
     */
    @Override
    public void setMaritalStatus(int maritalStatus) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getRaceId()
     */
    @Override
    public int getRaceId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setRaceId(int)
     */
    @Override
    public void setRaceId(int raceId) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getEmail()
     */
    @Override
    public String getEmail() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setEmail(java.lang.String)
     */
    @Override
    public void setEmail(String email) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getGroupId()
     */
    @Override
    public int getGroupId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setGroupId(int)
     */
    @Override
    public void setGroupId(int grpId) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#getGroupDescription()
     */
    @Override
    public String getGrp() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setGroupDescription(java.lang.String)
     */
    @Override
    public void setGrp(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#isLoggedIn()
     */
    @Override
    public int getLoggedIn() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.UserDto#setLoggedIn(boolean)
     */
    @Override
    public void setLoggedIn(int flag) {
        return;
    }

    /**
     * @return the queryLoggedInFlag
     */
    public boolean isQueryLoggedInFlag() {
        return queryLoggedInFlag;
    }

    /**
     * @param queryLoggedInFlag
     *            the queryLoggedInFlag to set
     */
    public void setQueryLoggedInFlag(boolean queryLoggedInFlag) {
        this.queryLoggedInFlag = queryLoggedInFlag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getTitleName()
     */
    @Override
    public String getTitleName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setTitleName(java.lang.String)
     */
    @Override
    public void setTitleName(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getAddress()
     */
    @Override
    public String getAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setAddress(java.lang.String)
     */
    @Override
    public void setAddress(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getCity()
     */
    @Override
    public String getCity() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getState()
     */
    @Override
    public String getState() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getZip()
     */
    @Override
    public String getZip() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setZip(java.lang.String)
     */
    @Override
    public void setZip(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getCountry()
     */
    @Override
    public String getCountry() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setCountry(java.lang.String)
     */
    @Override
    public void setCountry(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getHomePhone()
     */
    @Override
    public String getHomePhone() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setHomePhone(java.lang.String)
     */
    @Override
    public void setHomePhone(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getOfficePhone()
     */
    @Override
    public String getOfficePhone() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setOfficePhone(java.lang.String)
     */
    @Override
    public void setOfficePhone(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getMobilePhone()
     */
    @Override
    public String getMobilePhone() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setMobilePhone(java.lang.String)
     */
    @Override
    public void setMobilePhone(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getFax()
     */
    @Override
    public String getFax() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setFax(java.lang.String)
     */
    @Override
    public void setFax(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getOtherPhone()
     */
    @Override
    public String getOtherPhone() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setOtherPhone(java.lang.String)
     */
    @Override
    public void setOtherPhone(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getPager()
     */
    @Override
    public String getPager() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setPager(java.lang.String)
     */
    @Override
    public void setPager(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getJobTitleDescription()
     */
    @Override
    public String getJobTitleDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setJobTitleDescription(java.lang.String)
     */
    @Override
    public void setJobTitleDescription(String value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#getGrpDescription()
     */
    @Override
    public String getGrpDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.security.User#setGrpDescription(java.lang.String)
     */
    @Override
    public void setGrpDescription(String value) {
        // TODO Auto-generated method stub

    }

}