package org.entity;

import java.util.List;

/**
 * Contract for a User instance.
 * 
 * @author rterrell
 *
 */
public interface User {

    /**
     * @return the uid
     */
    int getUid();

    /**
     * @param uid the uid to set
     */
    void setUid(int uid);

    /**
     * @return the loginId
     */
    String getLoginId();

    /**
     * @param loginId the loginId to set
     */
    void setLoginId(String loginId);

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @return the password
     */
    String getPassword();

    /**
     * @param password the password to set
     */
    void setPassword(String password);

    /**
     * @return the totalLogons
     */
    int getTotalLogons();

    /**
     * @param totalLogons the totalLogons to set
     */
    void setTotalLogons(int totalLogons);

    /**
     * @return the active
     */
    int getActive();

    /**
     * @param active the active to set
     */
    void setActive(int active);

    /**
     * @return the personId
     */
    int getPersonId();

    /**
     * @param personId the personId to set
     */
    void setPersonId(int personId);

    /**
     * @return the firstname
     */
    String getFirstname();

    /**
     * @param firstname the firstname to set
     */
    void setFirstname(String firstname);

    /**
     * @return the midname
     */
    String getMidname();

    /**
     * @param midname the midname to set
     */
    void setMidname(String midname);

    /**
     * @return the lastname
     */
    String getLastname();

    /**
     * @param lastname the lastname to set
     */
    void setLastname(String lastname);

    /**
     * @return the maidenname
     */
    String getMaidenname();

    /**
     * @param maidenname the maidenname to set
     */
    void setMaidenname(String maidenname);

    /**
     * @return the generation
     */
    String getGeneration();

    /**
     * @param generation the generation to set
     */
    void setGeneration(String generation);

    /**
     * @return the shortname
     */
    String getShortname();

    /**
     * @param shortname the shortname to set
     */
    void setShortname(String shortname);

    /**
     * @return the title
     */
    int getTitle();

    /**
     * @param title the title to set
     */
    void setTitle(int title);

    /**
     * @return the genderId
     */
    int getGenderId();

    /**
     * @param genderId the genderId to set
     */
    void setGenderId(int genderId);

    /**
     * @return the maritalStatus
     */
    int getMaritalStatus();

    /**
     * @param maritalStatus the maritalStatus to set
     */
    void setMaritalStatus(int maritalStatus);

    /**
     * @return the birthDate
     */
    java.util.Date getBirthDate();

    /**
     * @param birthDate the birthDate to set
     */
    void setBirthDate(java.util.Date birthDate);

    /**
     * @return the raceId
     */
    int getRaceId();

    /**
     * @param raceId the raceId to set
     */
    void setRaceId(int raceId);

    /**
     * @return the ssn
     */
    String getSsn();

    /**
     * @param ssn the ssn to set
     */
    void setSsn(String ssn);

    /**
     * @return the email
     */
    String getEmail();

    /**
     * @param email the email to set
     */
    void setEmail(String email);
    
    /**
     * @return the roles
     */
    public List<String> getRoles();

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles);
    
    /**
     * 
     * @param role
     */
    void addRole(String role);

}