package org.dao.mapping.orm.rmt2;


import com.SystemException;
import com.api.persistence.db.orm.OrmBean;

/**
 * Peer object that maps to the group_roles database table/view.
 *
 * 
 */
public class GroupRoles extends OrmBean {




	// Property name constants that belong to respective DataSource, GroupRolesView

/** The property name constant equivalent to property, GrpRoleId, of respective DataSource view. */
  public static final String PROP_GRPROLEID = "GrpRoleId";
/** The property name constant equivalent to property, GrpId, of respective DataSource view. */
  public static final String PROP_GRPID = "GrpId";
/** The property name constant equivalent to property, RoleId, of respective DataSource view. */
  public static final String PROP_ROLEID = "RoleId";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column group_roles.grp_role_id */
  private int grpRoleId;
/** The javabean property equivalent of database column group_roles.grp_id */
  private int grpId;
/** The javabean property equivalent of database column group_roles.role_id */
  private int roleId;
/** The javabean property equivalent of database column group_roles.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column group_roles.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column group_roles.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 *
 * 
 */
  public GroupRoles() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable grpRoleId
 *
 * 
 */
  public void setGrpRoleId(int value) {
    this.grpRoleId = value;
  }
/**
 * Gets the value of member variable grpRoleId
 *
 * 
 */
  public int getGrpRoleId() {
    return this.grpRoleId;
  }
/**
 * Sets the value of member variable grpId
 *
 * 
 */
  public void setGrpId(int value) {
    this.grpId = value;
  }
/**
 * Gets the value of member variable grpId
 *
 * 
 */
  public int getGrpId() {
    return this.grpId;
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
 * Sets the value of member variable dateCreated
 *
 * 
 */
  public void setDateCreated(java.util.Date value) {
    this.dateCreated = value;
  }
/**
 * Gets the value of member variable dateCreated
 *
 * 
 */
  public java.util.Date getDateCreated() {
    return this.dateCreated;
  }
/**
 * Sets the value of member variable dateUpdated
 *
 * 
 */
  public void setDateUpdated(java.util.Date value) {
    this.dateUpdated = value;
  }
/**
 * Gets the value of member variable dateUpdated
 *
 * 
 */
  public java.util.Date getDateUpdated() {
    return this.dateUpdated;
  }
/**
 * Sets the value of member variable userId
 *
 * 
 */
  public void setUserId(String value) {
    this.userId = value;
  }
/**
 * Gets the value of member variable userId
 *
 * 
 */
  public String getUserId() {
    return this.userId;
  }
/**
 * Stubbed initialization method designed to implemented by developer.

 *
 * 
 */
  public void initBean() throws SystemException {}
}