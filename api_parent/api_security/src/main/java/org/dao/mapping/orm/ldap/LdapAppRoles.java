package org.dao.mapping.orm.ldap;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * ORM bean for mapping application role related data coming from a LDAP server.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapAppRoles extends LdapCommonEntity {

    private String appId;

    private String roleId;

    private String appRoleName;

    /**
     * Creates a LdapAppRoles object withou intializing any of its properties.
     */
    public LdapAppRoles() {
        super();
    }

    /**
     * Return the application id.
     * 
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Set the application id.
     * 
     * @param appId
     *            the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * Get role id
     * 
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Set the role id
     * 
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the application role name.
     * 
     * @return the appRoleName
     */
    public String getAppRoleName() {
        return appRoleName;
    }

    /**
     * Set the application role name.
     * 
     * @param appRoleName
     *            the appRoleName to set
     */
    public void setAppRoleName(String appRoleName) {
        this.appRoleName = appRoleName;
    }

}
