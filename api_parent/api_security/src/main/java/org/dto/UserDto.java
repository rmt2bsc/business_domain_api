package org.dto;

import java.util.List;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents user related information.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * 
 */
public interface UserDto extends TransactionDto {

    /**
     * Sets the value of member variable loginUid
     * 
     * 
     */
    void setLoginUid(int value);

    /**
     * Gets the value of member variable loginUid
     * 
     * 
     */
    int getLoginUid();

    /**
     * Sets the value of member variable username
     * 
     * 
     */
    void setUsername(String value);

    /**
     * Gets the value of member variable username
     * 
     * 
     */
    String getUsername();

    /**
     * Sets the value of member variable active
     * 
     * 
     */
    void setActive(int value);

    /**
     * Gets the value of member variable active
     * 
     * 
     */
    int getActive();

    /**
     * Sets the value of member variable firstname
     * 
     * 
     */
    void setFirstname(String value);

    /**
     * Gets the value of member variable firstname
     * 
     * 
     */
    String getFirstname();

    /**
     * Sets the value of member variable lastname
     * 
     * 
     */
    void setLastname(String value);

    /**
     * Gets the value of member variable lastname
     * 
     * 
     */
    String getLastname();

    /**
     * Sets the value of member variable birthDate
     * 
     * 
     */
    void setBirthDate(java.util.Date value);

    /**
     * Gets the value of member variable birthDate
     * 
     * 
     */
    java.util.Date getBirthDate();

    /**
     * Sets the value of member variable ssn
     * 
     * 
     */
    void setSsn(String value);

    /**
     * Gets the value of member variable ssn
     * 
     * 
     */
    String getSsn();

    /**
     * Sets the value of member variable startDate
     * 
     * 
     */
    void setStartDate(java.util.Date value);

    /**
     * Gets the value of member variable startDate
     * 
     * 
     */
    java.util.Date getStartDate();

    /**
     * Sets the value of member variable terminationDate
     * 
     * 
     */
    void setTerminationDate(java.util.Date value);

    /**
     * Gets the value of member variable terminationDate
     * 
     * 
     */
    java.util.Date getTerminationDate();

    /**
     * Sets the value of member variable email
     * 
     * 
     */
    void setEmail(String value);

    /**
     * Gets the value of member variable email
     * 
     * 
     */
    String getEmail();

    /**
     * Return the user's group id.
     * 
     * @return group id.
     */
    int getGroupId();

    /**
     * Set the user's group id.
     * 
     * @param grpId
     *            group id.
     */
    void setGroupId(int grpId);

    /**
     * Return the user's group description
     * 
     * @return group name.
     */
    String getGrp();

    /**
     * Set the user's group description
     * 
     * @param value
     *            group name.
     */
    void setGrp(String value);

    /**
     * Return the user's group description
     * 
     * @return group description.
     */
    String getGrpDescription();

    /**
     * Set the user's group description
     * 
     * @param value
     *            group description.
     */
    void setGrpDescription(String value);

    /**
     * Returns the user's login status.
     * 
     * @return true when user is logged in to one or more applications.
     *         Otherwise, false is returned.
     */
    int getLoggedIn();

    /**
     * Sets the user's login status.
     * 
     * @param flag
     *            true = login and false = not logged in
     */
    void setLoggedIn(int flag);

    /**
     * Get the user's password
     * 
     * @return user's password
     */
    String getPassword();

    /**
     * Set the user's password
     * 
     * @param password
     *            the user's password
     */
    void setPassword(String password);

    /**
     * Returns the total number of times the user has logged on.
     * 
     * @return int
     */
    int getTotalLogons();

    /**
     * Sets the total number of times the user has logged on.
     * 
     * @param value
     *            int value represting the total number of times the user has
     *            signed on.
     */
    void setTotalLogons(int value);

    /**
     * @return the queryLoggedInFlag
     */
    boolean isQueryLoggedInFlag();

    /**
     * @param queryLoggedInFlag
     *            the queryLoggedInFlag to set
     */
    void setQueryLoggedInFlag(boolean queryLoggedInFlag);

    /**
     * Get the user's job title description
     * 
     * @return user's job title description
     */
    String getJobTitleDescription();

    /**
     * Set the user's job title description
     * 
     * @param value
     *            the user's job title description
     */
    void setJobTitleDescription(String value);

    /**
     * @return the description
     */
    String getUserDescription();

    /**
     * the description to set
     * 
     * @param description
     */
    void setUserDescription(String description);

    /**
     * Get Address
     * 
     * @return String
     */
    String getAddress();

    /**
     * Set Address
     * 
     * @param value
     */
    void setAddress(String value);

    /**
     * Get City
     * 
     * @return String
     */
    String getCity();

    /**
     * Set City
     * 
     * @param value
     */
    void setCity(String value);

    /**
     * Get State
     * 
     * @return String
     */
    String getState();

    /**
     * Set State
     * 
     * @param value
     */
    void setState(String value);

    /**
     * Get Zip
     * 
     * @return String
     */
    String getZip();

    /**
     * Set Zip
     * 
     * @param value
     */
    void setZip(String value);

    /**
     * Get Country
     * 
     * @return String
     */
    String getCountry();

    /**
     * Set Country
     * 
     * @param value
     */
    void setCountry(String value);

    /**
     * Get Home Phone
     * 
     * @return String
     */
    String getHomePhone();

    /**
     * Set Home Phone
     * 
     * @param value
     */
    void setHomePhone(String value);

    /**
     * Get Work Phone
     * 
     * @return String
     */
    String getOfficePhone();

    /**
     * Set Work Phone
     * 
     * @param value
     */
    void setOfficePhone(String value);

    /**
     * Get Mobile Phone
     * 
     * @return String
     */
    String getMobilePhone();

    /**
     * Set mobile phone
     * 
     * @param value
     */
    void setMobilePhone(String value);

    /**
     * Get Fax.
     * 
     * @return String
     */
    String getFax();

    /**
     * Set Fax.
     * 
     * @param value
     */
    void setFax(String value);

    /**
     * Get other phone
     * 
     * @return String
     */
    String getOtherPhone();

    /**
     * Set other phone
     * 
     * @param value
     */
    void setOtherPhone(String value);

    /**
     * Get pager number
     * 
     * @return String
     */
    String getPager();

    /**
     * Set pager number
     * 
     * @param value
     */
    void setPager(String value);

    /**
     * Set the user's roles
     * 
     * @param value
     *            List<String>
     */
    void setRoles(List<String> value);

    /**
     * get the user's roles.
     * 
     * @return List<String>
     */
    List<String> getRoles();

    /**
     * the title Description
     * 
     * @return String
     */
    String getTitleName();

    /**
     * @param title
     *            the title description
     */
    void setTitleName(String title);
}