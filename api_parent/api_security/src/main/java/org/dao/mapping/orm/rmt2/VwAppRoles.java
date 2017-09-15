package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_app_roles database table/view.
 *
 * @author auto generated.
 */
public class VwAppRoles extends OrmBean {




	// Property name constants that belong to respective DataSource, VwAppRolesView

/** The property name constant equivalent to property, AppRoleId, of respective DataSource view. */
  public static final String PROP_APPROLEID = "AppRoleId";
/** The property name constant equivalent to property, AppRoleCode, of respective DataSource view. */
  public static final String PROP_APPROLECODE = "AppRoleCode";
/** The property name constant equivalent to property, AppRoleName, of respective DataSource view. */
  public static final String PROP_APPROLENAME = "AppRoleName";
/** The property name constant equivalent to property, AppRoleDescription, of respective DataSource view. */
  public static final String PROP_APPROLEDESCRIPTION = "AppRoleDescription";
/** The property name constant equivalent to property, RoleId, of respective DataSource view. */
  public static final String PROP_ROLEID = "RoleId";
/** The property name constant equivalent to property, RoleName, of respective DataSource view. */
  public static final String PROP_ROLENAME = "RoleName";
/** The property name constant equivalent to property, AppName, of respective DataSource view. */
  public static final String PROP_APPNAME = "AppName";
/** The property name constant equivalent to property, ApplicationId, of respective DataSource view. */
  public static final String PROP_APPLICATIONID = "ApplicationId";



	/** The javabean property equivalent of database column vw_app_roles.app_role_id */
  private int appRoleId;
/** The javabean property equivalent of database column vw_app_roles.app_role_code */
  private String appRoleCode;
/** The javabean property equivalent of database column vw_app_roles.app_role_name */
  private String appRoleName;
/** The javabean property equivalent of database column vw_app_roles.app_role_description */
  private String appRoleDescription;
/** The javabean property equivalent of database column vw_app_roles.role_id */
  private int roleId;
/** The javabean property equivalent of database column vw_app_roles.role_name */
  private String roleName;
/** The javabean property equivalent of database column vw_app_roles.app_name */
  private String appName;
/** The javabean property equivalent of database column vw_app_roles.application_id */
  private int applicationId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwAppRoles() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable appRoleId
 */
  public void setAppRoleId(int value) {
    this.appRoleId = value;
  }
/**
 * Gets the value of member variable appRoleId
 */
  public int getAppRoleId() {
    return this.appRoleId;
  }
/**
 * Sets the value of member variable appRoleCode
 */
  public void setAppRoleCode(String value) {
    this.appRoleCode = value;
  }
/**
 * Gets the value of member variable appRoleCode
 */
  public String getAppRoleCode() {
    return this.appRoleCode;
  }
/**
 * Sets the value of member variable appRoleName
 */
  public void setAppRoleName(String value) {
    this.appRoleName = value;
  }
/**
 * Gets the value of member variable appRoleName
 */
  public String getAppRoleName() {
    return this.appRoleName;
  }
/**
 * Sets the value of member variable appRoleDescription
 */
  public void setAppRoleDescription(String value) {
    this.appRoleDescription = value;
  }
/**
 * Gets the value of member variable appRoleDescription
 */
  public String getAppRoleDescription() {
    return this.appRoleDescription;
  }
/**
 * Sets the value of member variable roleId
 */
  public void setRoleId(int value) {
    this.roleId = value;
  }
/**
 * Gets the value of member variable roleId
 */
  public int getRoleId() {
    return this.roleId;
  }
/**
 * Sets the value of member variable roleName
 */
  public void setRoleName(String value) {
    this.roleName = value;
  }
/**
 * Gets the value of member variable roleName
 */
  public String getRoleName() {
    return this.roleName;
  }
/**
 * Sets the value of member variable appName
 */
  public void setAppName(String value) {
    this.appName = value;
  }
/**
 * Gets the value of member variable appName
 */
  public String getAppName() {
    return this.appName;
  }
/**
 * Sets the value of member variable applicationId
 */
  public void setApplicationId(int value) {
    this.applicationId = value;
  }
/**
 * Gets the value of member variable applicationId
 */
  public int getApplicationId() {
    return this.applicationId;
  }

@Override
public boolean equals(Object obj) {
   if (this == obj) {
      return true;
   }
   if (obj == null) {
      return false;
   }
   if (getClass() != obj.getClass()) {
      return false;
   }
   final VwAppRoles other = (VwAppRoles) obj; 
   if (EqualityAssistant.notEqual(this.appRoleId, other.appRoleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appRoleCode, other.appRoleCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appRoleName, other.appRoleName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appRoleDescription, other.appRoleDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.roleId, other.roleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.roleName, other.roleName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appName, other.appName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.applicationId, other.applicationId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.appRoleId),
               HashCodeAssistant.hashObject(this.appRoleCode),
               HashCodeAssistant.hashObject(this.appRoleName),
               HashCodeAssistant.hashObject(this.appRoleDescription),
               HashCodeAssistant.hashObject(this.roleId),
               HashCodeAssistant.hashObject(this.roleName),
               HashCodeAssistant.hashObject(this.appName),
               HashCodeAssistant.hashObject(this.applicationId));
} 

@Override
public String toString() {
   return "VwAppRoles [appRoleId=" + appRoleId + 
          ", appRoleCode=" + appRoleCode + 
          ", appRoleName=" + appRoleName + 
          ", appRoleDescription=" + appRoleDescription + 
          ", roleId=" + roleId + 
          ", roleName=" + roleName + 
          ", appName=" + appName + 
          ", applicationId=" + applicationId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}