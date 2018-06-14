package org.dto;

/**
 * Data Transfer Object (DTO) contract that consolidates information pertaining
 * to the User, User Group, Application, Role, and the Application Role
 * entities.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * 
 */
public interface CategoryDto extends UserDto {

    /**
     * Sets the value of member variable userAppRoleId
     * 
     * 
     */
    void setUserAppRoleId(int value);

    /**
     * Gets the value of member variable userAppRoleId
     * 
     * 
     */
    int getUserAppRoleId();
    
    /**
     * Sets the value of member variable appRoleId
     * 
     * 
     */
    void setAppRoleId(int value);

    /**
     * Gets the value of member variable appRoleId
     * 
     * 
     */
    int getAppRoleId();

    /**
     * Sets the value of member variable appRoleCode
     * 
     * 
     */
    void setAppRoleCode(String value);

    /**
     * Gets the value of member variable appRoleCode
     * 
     * 
     */
    String getAppRoleCode();

    /**
     * Sets the value of member variable appRoleName
     * 
     * 
     */
    void setAppRoleName(String value);

    /**
     * Gets the value of member variable appRoleName
     * 
     * 
     */
    String getAppRoleName();

    /**
     * Sets the value of member variable appRoleDescription
     * 
     * 
     */
    void setAppRoleDescription(String value);

    /**
     * Gets the value of member variable appRoleDescription
     * 
     * 
     */
    String getAppRoleDescription();

    /**
     * Sets the value of member variable roleId
     * 
     * 
     */
    void setRoleId(int value);

    /**
     * Gets the value of member variable roleId
     * 
     * 
     */
    int getRoleId();

    /**
     * Sets the value of member variable roleName
     * 
     * 
     */
    void setRoleName(String value);

    /**
     * Set the value of role description
     * 
     * @param value
     *            description
     */
    void setRoleDescription(String value);

    /**
     * Get the role descritpion.
     * 
     * @return description
     */
    String getRoleDescription();

    /**
     * Gets the value of member variable roleName
     * 
     * 
     */
    String getRoleName();

    /**
     * Sets the value of member variable applicationId
     * 
     * 
     */
    void setApplicationId(int value);

    /**
     * Gets the value of member variable applicationId
     * 
     * 
     */
    int getApplicationId();

    /**
     * Sets the value of member variable appName
     * 
     * 
     */
    void setAppName(String value);

    /**
     * Gets the value of member variable appName
     * 
     * 
     */
    String getAppName();

    /**
     * Sets the description of the application
     * 
     * 
     */
    void setAppDescription(String value);

    /**
     * Gets description of the application
     * 
     * 
     */
    String getAppDescription();

}