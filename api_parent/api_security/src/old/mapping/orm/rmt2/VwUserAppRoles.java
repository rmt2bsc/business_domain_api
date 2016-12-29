package org.dao.mapping.orm.rmt2;


import com.SystemException;
import com.api.persistence.db.orm.OrmBean;

/**
 * Peer object that maps to the vw_user_app_roles database table/view.
 *
 * 
 */
public class VwUserAppRoles extends OrmBean {




	// Property name constants that belong to respective DataSource, VwUserAppRolesView

/** The property name constant equivalent to property, Email, of respective DataSource view. */
  public static final String PROP_EMAIL = "Email";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
/** The property name constant equivalent to property, GroupId, of respective DataSource view. */
  public static final String PROP_GROUPID = "GroupId";
/** The property name constant equivalent to property, GroupDescription, of respective DataSource view. */
  public static final String PROP_GROUPDESCRIPTION = "GroupDescription";
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
/** The property name constant equivalent to property, LoginUid, of respective DataSource view. */
  public static final String PROP_LOGINUID = "LoginUid";
/** The property name constant equivalent to property, Username, of respective DataSource view. */
  public static final String PROP_USERNAME = "Username";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, BirthDate, of respective DataSource view. */
  public static final String PROP_BIRTHDATE = "BirthDate";
/** The property name constant equivalent to property, Ssn, of respective DataSource view. */
  public static final String PROP_SSN = "Ssn";
/** The property name constant equivalent to property, UserDescription, of respective DataSource view. */
  public static final String PROP_USERDESCRIPTION = "UserDescription";
/** The property name constant equivalent to property, StartDate, of respective DataSource view. */
  public static final String PROP_STARTDATE = "StartDate";
/** The property name constant equivalent to property, TerminationDate, of respective DataSource view. */
  public static final String PROP_TERMINATIONDATE = "TerminationDate";



	/** The javabean property equivalent of database column vw_user_app_roles.email */
  private String email;
/** The javabean property equivalent of database column vw_user_app_roles.active */
  private int active;
/** The javabean property equivalent of database column vw_user_app_roles.group_id */
  private int groupId;
/** The javabean property equivalent of database column vw_user_app_roles.group_description */
  private String groupDescription;
/** The javabean property equivalent of database column vw_user_app_roles.app_role_id */
  private int appRoleId;
/** The javabean property equivalent of database column vw_user_app_roles.app_role_code */
  private String appRoleCode;
/** The javabean property equivalent of database column vw_user_app_roles.app_role_name */
  private String appRoleName;
/** The javabean property equivalent of database column vw_user_app_roles.app_role_description */
  private String appRoleDescription;
/** The javabean property equivalent of database column vw_user_app_roles.role_id */
  private int roleId;
/** The javabean property equivalent of database column vw_user_app_roles.role_name */
  private String roleName;
/** The javabean property equivalent of database column vw_user_app_roles.app_name */
  private String appName;
/** The javabean property equivalent of database column vw_user_app_roles.application_id */
  private int applicationId;
/** The javabean property equivalent of database column vw_user_app_roles.login_uid */
  private int loginUid;
/** The javabean property equivalent of database column vw_user_app_roles.username */
  private String username;
/** The javabean property equivalent of database column vw_user_app_roles.firstname */
  private String firstname;
/** The javabean property equivalent of database column vw_user_app_roles.lastname */
  private String lastname;
/** The javabean property equivalent of database column vw_user_app_roles.birth_date */
  private java.util.Date birthDate;
/** The javabean property equivalent of database column vw_user_app_roles.ssn */
  private String ssn;
/** The javabean property equivalent of database column vw_user_app_roles.user_description */
  private String userDescription;
/** The javabean property equivalent of database column vw_user_app_roles.start_date */
  private java.util.Date startDate;
/** The javabean property equivalent of database column vw_user_app_roles.termination_date */
  private java.util.Date terminationDate;



	// Getter/Setter Methods

/**
 * Default constructor.
 *
 * 
 */
  public VwUserAppRoles() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable email
 *
 * 
 */
  public void setEmail(String value) {
    this.email = value;
  }
/**
 * Gets the value of member variable email
 *
 * 
 */
  public String getEmail() {
    return this.email;
  }
/**
 * Sets the value of member variable active
 *
 * 
 */
  public void setActive(int value) {
    this.active = value;
  }
/**
 * Gets the value of member variable active
 *
 * 
 */
  public int getActive() {
    return this.active;
  }
/**
 * Sets the value of member variable groupId
 *
 * 
 */
  public void setGroupId(int value) {
    this.groupId = value;
  }
/**
 * Gets the value of member variable groupId
 *
 * 
 */
  public int getGroupId() {
    return this.groupId;
  }
/**
 * Sets the value of member variable groupDescription
 *
 * 
 */
  public void setGroupDescription(String value) {
    this.groupDescription = value;
  }
/**
 * Gets the value of member variable groupDescription
 *
 * 
 */
  public String getGroupDescription() {
    return this.groupDescription;
  }
/**
 * Sets the value of member variable appRoleId
 *
 * 
 */
  public void setAppRoleId(int value) {
    this.appRoleId = value;
  }
/**
 * Gets the value of member variable appRoleId
 *
 * 
 */
  public int getAppRoleId() {
    return this.appRoleId;
  }
/**
 * Sets the value of member variable appRoleCode
 *
 * 
 */
  public void setAppRoleCode(String value) {
    this.appRoleCode = value;
  }
/**
 * Gets the value of member variable appRoleCode
 *
 * 
 */
  public String getAppRoleCode() {
    return this.appRoleCode;
  }
/**
 * Sets the value of member variable appRoleName
 *
 * 
 */
  public void setAppRoleName(String value) {
    this.appRoleName = value;
  }
/**
 * Gets the value of member variable appRoleName
 *
 * 
 */
  public String getAppRoleName() {
    return this.appRoleName;
  }
/**
 * Sets the value of member variable appRoleDescription
 *
 * 
 */
  public void setAppRoleDescription(String value) {
    this.appRoleDescription = value;
  }
/**
 * Gets the value of member variable appRoleDescription
 *
 * 
 */
  public String getAppRoleDescription() {
    return this.appRoleDescription;
  }
/**
 * Sets the value of member variable roleId
 *
 * 
 */
  public void setRoleId(int value) {
    this.roleId = value;
  }
/**
 * Gets the value of member variable roleId
 *
 * 
 */
  public int getRoleId() {
    return this.roleId;
  }
/**
 * Sets the value of member variable roleName
 *
 * 
 */
  public void setRoleName(String value) {
    this.roleName = value;
  }
/**
 * Gets the value of member variable roleName
 *
 * 
 */
  public String getRoleName() {
    return this.roleName;
  }
/**
 * Sets the value of member variable appName
 *
 * 
 */
  public void setAppName(String value) {
    this.appName = value;
  }
/**
 * Gets the value of member variable appName
 *
 * 
 */
  public String getAppName() {
    return this.appName;
  }
/**
 * Sets the value of member variable applicationId
 *
 * 
 */
  public void setApplicationId(int value) {
    this.applicationId = value;
  }
/**
 * Gets the value of member variable applicationId
 *
 * 
 */
  public int getApplicationId() {
    return this.applicationId;
  }
/**
 * Sets the value of member variable loginUid
 *
 * 
 */
  public void setLoginUid(int value) {
    this.loginUid = value;
  }
/**
 * Gets the value of member variable loginUid
 *
 * 
 */
  public int getLoginUid() {
    return this.loginUid;
  }
/**
 * Sets the value of member variable username
 *
 * 
 */
  public void setUsername(String value) {
    this.username = value;
  }
/**
 * Gets the value of member variable username
 *
 * 
 */
  public String getUsername() {
    return this.username;
  }
/**
 * Sets the value of member variable firstname
 *
 * 
 */
  public void setFirstname(String value) {
    this.firstname = value;
  }
/**
 * Gets the value of member variable firstname
 *
 * 
 */
  public String getFirstname() {
    return this.firstname;
  }
/**
 * Sets the value of member variable lastname
 *
 * 
 */
  public void setLastname(String value) {
    this.lastname = value;
  }
/**
 * Gets the value of member variable lastname
 *
 * 
 */
  public String getLastname() {
    return this.lastname;
  }
/**
 * Sets the value of member variable birthDate
 *
 * 
 */
  public void setBirthDate(java.util.Date value) {
    this.birthDate = value;
  }
/**
 * Gets the value of member variable birthDate
 *
 * 
 */
  public java.util.Date getBirthDate() {
    return this.birthDate;
  }
/**
 * Sets the value of member variable ssn
 *
 * 
 */
  public void setSsn(String value) {
    this.ssn = value;
  }
/**
 * Gets the value of member variable ssn
 *
 * 
 */
  public String getSsn() {
    return this.ssn;
  }
/**
 * Sets the value of member variable userDescription
 *
 * 
 */
  public void setUserDescription(String value) {
    this.userDescription = value;
  }
/**
 * Gets the value of member variable userDescription
 *
 * 
 */
  public String getUserDescription() {
    return this.userDescription;
  }
/**
 * Sets the value of member variable startDate
 *
 * 
 */
  public void setStartDate(java.util.Date value) {
    this.startDate = value;
  }
/**
 * Gets the value of member variable startDate
 *
 * 
 */
  public java.util.Date getStartDate() {
    return this.startDate;
  }
/**
 * Sets the value of member variable terminationDate
 *
 * 
 */
  public void setTerminationDate(java.util.Date value) {
    this.terminationDate = value;
  }
/**
 * Gets the value of member variable terminationDate
 *
 * 
 */
  public java.util.Date getTerminationDate() {
    return this.terminationDate;
  }
/**
 * Stubbed initialization method designed to implemented by developer.

 *
 * 
 */
  public void initBean() throws SystemException {}
}