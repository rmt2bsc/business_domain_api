package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_user_resource_access database table/view.
 *
 * @author auto generated.
 */
public class VwUserResourceAccess extends OrmBean {




	// Property name constants that belong to respective DataSource, VwUserResourceAccessView

/** The property name constant equivalent to property, RsrcId, of respective DataSource view. */
  public static final String PROP_RSRCID = "RsrcId";
/** The property name constant equivalent to property, ResrcName, of respective DataSource view. */
  public static final String PROP_RESRCNAME = "ResrcName";
/** The property name constant equivalent to property, ResrcUrl, of respective DataSource view. */
  public static final String PROP_RESRCURL = "ResrcUrl";
/** The property name constant equivalent to property, ResrcDesc, of respective DataSource view. */
  public static final String PROP_RESRCDESC = "ResrcDesc";
/** The property name constant equivalent to property, ResrcSecured, of respective DataSource view. */
  public static final String PROP_RESRCSECURED = "ResrcSecured";
/** The property name constant equivalent to property, RsrcTypeId, of respective DataSource view. */
  public static final String PROP_RSRCTYPEID = "RsrcTypeId";
/** The property name constant equivalent to property, ResrcTypeName, of respective DataSource view. */
  public static final String PROP_RESRCTYPENAME = "ResrcTypeName";
/** The property name constant equivalent to property, RsrcSubtypeId, of respective DataSource view. */
  public static final String PROP_RSRCSUBTYPEID = "RsrcSubtypeId";
/** The property name constant equivalent to property, Host, of respective DataSource view. */
  public static final String PROP_HOST = "Host";
/** The property name constant equivalent to property, ResrcSubtypeName, of respective DataSource view. */
  public static final String PROP_RESRCSUBTYPENAME = "ResrcSubtypeName";
/** The property name constant equivalent to property, ResrcSubtypeDesc, of respective DataSource view. */
  public static final String PROP_RESRCSUBTYPEDESC = "ResrcSubtypeDesc";
/** The property name constant equivalent to property, UserUid, of respective DataSource view. */
  public static final String PROP_USERUID = "UserUid";
/** The property name constant equivalent to property, Username, of respective DataSource view. */
  public static final String PROP_USERNAME = "Username";
/** The property name constant equivalent to property, UserFirstname, of respective DataSource view. */
  public static final String PROP_USERFIRSTNAME = "UserFirstname";
/** The property name constant equivalent to property, UserLastname, of respective DataSource view. */
  public static final String PROP_USERLASTNAME = "UserLastname";
/** The property name constant equivalent to property, UserActiveStatus, of respective DataSource view. */
  public static final String PROP_USERACTIVESTATUS = "UserActiveStatus";
/** The property name constant equivalent to property, UserEmail, of respective DataSource view. */
  public static final String PROP_USEREMAIL = "UserEmail";
/** The property name constant equivalent to property, UserGroupId, of respective DataSource view. */
  public static final String PROP_USERGROUPID = "UserGroupId";
/** The property name constant equivalent to property, UserGroupName, of respective DataSource view. */
  public static final String PROP_USERGROUPNAME = "UserGroupName";



	/** The javabean property equivalent of database column vw_user_resource_access.rsrc_id */
  private int rsrcId;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_name */
  private String resrcName;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_url */
  private String resrcUrl;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_desc */
  private String resrcDesc;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_secured */
  private int resrcSecured;
/** The javabean property equivalent of database column vw_user_resource_access.rsrc_type_id */
  private int rsrcTypeId;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_type_name */
  private String resrcTypeName;
/** The javabean property equivalent of database column vw_user_resource_access.rsrc_subtype_id */
  private int rsrcSubtypeId;
/** The javabean property equivalent of database column vw_user_resource_access.host */
  private String host;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_subtype_name */
  private String resrcSubtypeName;
/** The javabean property equivalent of database column vw_user_resource_access.resrc_subtype_desc */
  private String resrcSubtypeDesc;
/** The javabean property equivalent of database column vw_user_resource_access.user_uid */
  private int userUid;
/** The javabean property equivalent of database column vw_user_resource_access.username */
  private String username;
/** The javabean property equivalent of database column vw_user_resource_access.user_firstname */
  private String userFirstname;
/** The javabean property equivalent of database column vw_user_resource_access.user_lastname */
  private String userLastname;
/** The javabean property equivalent of database column vw_user_resource_access.user_active_status */
  private int userActiveStatus;
/** The javabean property equivalent of database column vw_user_resource_access.user_email */
  private String userEmail;
/** The javabean property equivalent of database column vw_user_resource_access.user_group_id */
  private int userGroupId;
/** The javabean property equivalent of database column vw_user_resource_access.user_group_name */
  private String userGroupName;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwUserResourceAccess() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable rsrcId
 */
  public void setRsrcId(int value) {
    this.rsrcId = value;
  }
/**
 * Gets the value of member variable rsrcId
 */
  public int getRsrcId() {
    return this.rsrcId;
  }
/**
 * Sets the value of member variable resrcName
 */
  public void setResrcName(String value) {
    this.resrcName = value;
  }
/**
 * Gets the value of member variable resrcName
 */
  public String getResrcName() {
    return this.resrcName;
  }
/**
 * Sets the value of member variable resrcUrl
 */
  public void setResrcUrl(String value) {
    this.resrcUrl = value;
  }
/**
 * Gets the value of member variable resrcUrl
 */
  public String getResrcUrl() {
    return this.resrcUrl;
  }
/**
 * Sets the value of member variable resrcDesc
 */
  public void setResrcDesc(String value) {
    this.resrcDesc = value;
  }
/**
 * Gets the value of member variable resrcDesc
 */
  public String getResrcDesc() {
    return this.resrcDesc;
  }
/**
 * Sets the value of member variable resrcSecured
 */
  public void setResrcSecured(int value) {
    this.resrcSecured = value;
  }
/**
 * Gets the value of member variable resrcSecured
 */
  public int getResrcSecured() {
    return this.resrcSecured;
  }
/**
 * Sets the value of member variable rsrcTypeId
 */
  public void setRsrcTypeId(int value) {
    this.rsrcTypeId = value;
  }
/**
 * Gets the value of member variable rsrcTypeId
 */
  public int getRsrcTypeId() {
    return this.rsrcTypeId;
  }
/**
 * Sets the value of member variable resrcTypeName
 */
  public void setResrcTypeName(String value) {
    this.resrcTypeName = value;
  }
/**
 * Gets the value of member variable resrcTypeName
 */
  public String getResrcTypeName() {
    return this.resrcTypeName;
  }
/**
 * Sets the value of member variable rsrcSubtypeId
 */
  public void setRsrcSubtypeId(int value) {
    this.rsrcSubtypeId = value;
  }
/**
 * Gets the value of member variable rsrcSubtypeId
 */
  public int getRsrcSubtypeId() {
    return this.rsrcSubtypeId;
  }
/**
 * Sets the value of member variable host
 */
  public void setHost(String value) {
    this.host = value;
  }
/**
 * Gets the value of member variable host
 */
  public String getHost() {
    return this.host;
  }
/**
 * Sets the value of member variable resrcSubtypeName
 */
  public void setResrcSubtypeName(String value) {
    this.resrcSubtypeName = value;
  }
/**
 * Gets the value of member variable resrcSubtypeName
 */
  public String getResrcSubtypeName() {
    return this.resrcSubtypeName;
  }
/**
 * Sets the value of member variable resrcSubtypeDesc
 */
  public void setResrcSubtypeDesc(String value) {
    this.resrcSubtypeDesc = value;
  }
/**
 * Gets the value of member variable resrcSubtypeDesc
 */
  public String getResrcSubtypeDesc() {
    return this.resrcSubtypeDesc;
  }
/**
 * Sets the value of member variable userUid
 */
  public void setUserUid(int value) {
    this.userUid = value;
  }
/**
 * Gets the value of member variable userUid
 */
  public int getUserUid() {
    return this.userUid;
  }
/**
 * Sets the value of member variable username
 */
  public void setUsername(String value) {
    this.username = value;
  }
/**
 * Gets the value of member variable username
 */
  public String getUsername() {
    return this.username;
  }
/**
 * Sets the value of member variable userFirstname
 */
  public void setUserFirstname(String value) {
    this.userFirstname = value;
  }
/**
 * Gets the value of member variable userFirstname
 */
  public String getUserFirstname() {
    return this.userFirstname;
  }
/**
 * Sets the value of member variable userLastname
 */
  public void setUserLastname(String value) {
    this.userLastname = value;
  }
/**
 * Gets the value of member variable userLastname
 */
  public String getUserLastname() {
    return this.userLastname;
  }
/**
 * Sets the value of member variable userActiveStatus
 */
  public void setUserActiveStatus(int value) {
    this.userActiveStatus = value;
  }
/**
 * Gets the value of member variable userActiveStatus
 */
  public int getUserActiveStatus() {
    return this.userActiveStatus;
  }
/**
 * Sets the value of member variable userEmail
 */
  public void setUserEmail(String value) {
    this.userEmail = value;
  }
/**
 * Gets the value of member variable userEmail
 */
  public String getUserEmail() {
    return this.userEmail;
  }
/**
 * Sets the value of member variable userGroupId
 */
  public void setUserGroupId(int value) {
    this.userGroupId = value;
  }
/**
 * Gets the value of member variable userGroupId
 */
  public int getUserGroupId() {
    return this.userGroupId;
  }
/**
 * Sets the value of member variable userGroupName
 */
  public void setUserGroupName(String value) {
    this.userGroupName = value;
  }
/**
 * Gets the value of member variable userGroupName
 */
  public String getUserGroupName() {
    return this.userGroupName;
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
   final VwUserResourceAccess other = (VwUserResourceAccess) obj; 
   if (EqualityAssistant.notEqual(this.rsrcId, other.rsrcId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcName, other.resrcName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcUrl, other.resrcUrl)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcDesc, other.resrcDesc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSecured, other.resrcSecured)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.rsrcTypeId, other.rsrcTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcTypeName, other.resrcTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.rsrcSubtypeId, other.rsrcSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.host, other.host)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSubtypeName, other.resrcSubtypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSubtypeDesc, other.resrcSubtypeDesc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userUid, other.userUid)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.username, other.username)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userFirstname, other.userFirstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userLastname, other.userLastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userActiveStatus, other.userActiveStatus)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userEmail, other.userEmail)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userGroupId, other.userGroupId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userGroupName, other.userGroupName)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.rsrcId),
   HashCodeAssistant.hashObject(this.resrcName),
   HashCodeAssistant.hashObject(this.resrcUrl),
   HashCodeAssistant.hashObject(this.resrcDesc),
   HashCodeAssistant.hashObject(this.resrcSecured),
   HashCodeAssistant.hashObject(this.rsrcTypeId),
   HashCodeAssistant.hashObject(this.resrcTypeName),
   HashCodeAssistant.hashObject(this.rsrcSubtypeId),
   HashCodeAssistant.hashObject(this.host),
   HashCodeAssistant.hashObject(this.resrcSubtypeName),
   HashCodeAssistant.hashObject(this.resrcSubtypeDesc),
   HashCodeAssistant.hashObject(this.userUid),
   HashCodeAssistant.hashObject(this.username),
   HashCodeAssistant.hashObject(this.userFirstname),
   HashCodeAssistant.hashObject(this.userLastname),
   HashCodeAssistant.hashObject(this.userActiveStatus),
   HashCodeAssistant.hashObject(this.userEmail),
   HashCodeAssistant.hashObject(this.userGroupId),
   HashCodeAssistant.hashObject(this.userGroupName));
} 

@Override
public String toString() {
   return "VwUserResourceAccess [rsrcId=" + rsrcId + 
          ", resrcName=" + resrcName + 
          ", resrcUrl=" + resrcUrl + 
          ", resrcDesc=" + resrcDesc + 
          ", resrcSecured=" + resrcSecured + 
          ", rsrcTypeId=" + rsrcTypeId + 
          ", resrcTypeName=" + resrcTypeName + 
          ", rsrcSubtypeId=" + rsrcSubtypeId + 
          ", host=" + host + 
          ", resrcSubtypeName=" + resrcSubtypeName + 
          ", resrcSubtypeDesc=" + resrcSubtypeDesc + 
          ", userUid=" + userUid + 
          ", username=" + username + 
          ", userFirstname=" + userFirstname + 
          ", userLastname=" + userLastname + 
          ", userActiveStatus=" + userActiveStatus + 
          ", userEmail=" + userEmail + 
          ", userGroupId=" + userGroupId + 
          ", userGroupName=" + userGroupName  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}