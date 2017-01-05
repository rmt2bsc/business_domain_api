package org.dao.roles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.SecurityDaoImpl;
import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.Roles;
import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.RMT2Constants;
import com.SystemException;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * RMT2 ORM implementation for the {@link RoleDao} interface.
 * 
 * @author rterrell
 * 
 */
class Rmt2OrmRoleDaoImpl extends SecurityDaoImpl implements RoleDao {
    private static final Logger logger = Logger
            .getLogger(Rmt2OrmRoleDaoImpl.class);

    /**
     * Default constructor for creating a Rmt2OrmRoleDaoImpl.
     */
    protected Rmt2OrmRoleDaoImpl() {
        super();
    }

    /**
     * constructor for creating a Rmt2OrmRoleDaoImpl.
     * 
     * @param appName
     *            application name
     */
    protected Rmt2OrmRoleDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Fetches all the records in the roles table.
     * <p>
     * The result set is ordered by role name in ascendng order
     * 
     * @return A List of {@link CategoryDto} containing role data.
     * @throws RoleDaoException
     *             General database errors
     */
    @Override
    public List<CategoryDto> fetchRole() throws RoleDaoException {
        Roles roles = new Roles();
        roles.addOrderBy(Roles.PROP_NAME, Roles.ORDERBY_ASCENDING);
        List<Roles> roleList = null;
        try {
            roleList = this.client.retrieveList(roles);
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (Roles item : roleList) {
            CategoryDto dto = Rmt2OrmDtoFactory.getRoleDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the roles table for a single record key by <i>roleId</i>.
     * 
     * @param roleId
     *            The id of the role to retrieve.
     * @return an instance of {@link CategoryDto} containing the role data or
     *         null if not found
     * @throws UserAuthenticationException
     */
    @Override
    public CategoryDto fetchRole(int roleId) throws RoleDaoException {
        Roles roles = new Roles();
        roles.addCriteria(Roles.PROP_ROLEID, roleId);
        try {
            Roles r = (Roles) this.client.retrieveObject(roles);
            if (r == null) {
                return null;
            }
            return Rmt2OrmDtoFactory.getRoleDtoInstance(r);
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }
    }

    /**
     * Returns all records contained in the vw_app_role view of the database
     * 
     * @return a List of {@link CategoryDto} objects or null if no records are
     *         found.
     * @throws SecurityDaoException
     */
    @Override
    public List<CategoryDto> fetchAppRole() throws RoleDaoException {
        VwAppRoles obj = new VwAppRoles();
        List<VwAppRoles> roles = null;
        try {
            roles = this.client.retrieveList(obj);
            if (roles == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }
        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwAppRoles item : roles) {
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(item,
                    this.updateUserId);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the app_role table for a single record keyed by <i>uid</i>.
     * 
     * @param uid
     *            The unique identifier of the application role.
     * @return an instance of {@link CategoryDto}
     * @throws SecurityDaoException
     */
    @Override
    public CategoryDto fetchAppRole(int uid) throws RoleDaoException {
        AppRole obj = new AppRole();
        obj.addCriteria(AppRole.PROP_APPROLEID, uid);
        AppRole role = null;
        try {
            role = (AppRole) this.client.retrieveObject(obj);
            if (role == null) {
                return null;
            }
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(role);
            return dto;
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }
    }

    // /**
    // * Queries the vw_user_app_role view for one or more records by
    // <i>userName</i>.
    // *
    // * @param userName
    // * The user's login id.
    // * @return
    // * a List of {@link CategoryDto} objects or null if no records are found.
    // * @throws SecurityDaoException
    // */
    // @Override
    // public List<CategoryDto> fetchAppRole(String userName) throws
    // RoleDaoException {
    // VwUserAppRoles obj = new VwUserAppRoles();
    // obj.addCriteria(VwUserAppRoles.PROP_USERNAME, userName);
    // List<VwUserAppRoles> roles = null;
    // try {
    // roles = this.client.retrieveList(obj);
    // if (roles == null) {
    // return null;
    // }
    // }
    // catch (DatabaseException e) {
    // throw new RoleDaoException(e);
    // }
    // List<CategoryDto> list = new ArrayList<CategoryDto>();
    // for (VwUserAppRoles item : roles) {
    // CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item,
    // this.updateUserId);
    // list.add(dto);
    // }
    // return list;
    // }

    // /**
    // * Queries the vw_user_app_role view for records related to the user's
    // login id and an application.
    // *
    // * @param userName
    // * The user's login id.
    // * @param appName
    // * The name of the related application to retrieve roles.
    // * @return
    // * a List of {@link CategoryDto} objects or null if no records are found.
    // * @throws SecurityDaoException
    // */
    // @Override
    // public List<CategoryDto> fetchAppRole(String userName, String appName)
    // throws RoleDaoException {
    // VwUserAppRoles obj = new VwUserAppRoles();
    // obj.addCriteria(VwUserAppRoles.PROP_USERNAME, userName);
    // obj.addCriteria(VwUserAppRoles.PROP_APPNAME, appName);
    // List<VwUserAppRoles> roles = null;
    // try {
    // roles = this.client.retrieveList(obj);
    // if (roles == null) {
    // return null;
    // }
    // }
    // catch (DatabaseException e) {
    // throw new RoleDaoException(e);
    // }
    // List<CategoryDto> list = new ArrayList<CategoryDto>();
    // for (VwUserAppRoles item : roles) {
    // CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item,
    // this.updateUserId);
    // list.add(dto);
    // }
    // return list;
    // }

    /**
     * Queries the vw_app_role view for records based on the combination of
     * selection criteria contained in <i>criteria</i>.
     * <p>
     * The data values considered as selection criteria for this implementation
     * are application id (exact match), application name (begins with match),
     * role id (exact match), role name (begins with match), and application
     * role code (begins with match).
     * <p>
     * This method will combine the selection criteria noted in the legacy
     * methods from class, ApplicationBeanImpl, getAppRole(Application),
     * getAppRole(Application, Roles) and getAppRole(Roles).
     * 
     * @param criteria
     *            an instance of {@link CategoryDto}
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityDaoException
     */
    @Override
    public List<CategoryDto> fetchAppRole(CategoryDto criteria)
            throws RoleDaoException {
        VwAppRoles obj = new VwAppRoles();
        List<VwAppRoles> roles;
        if (criteria != null) {
            if (criteria.getApplicationId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_APPLICATIONID,
                        criteria.getApplicationId());
            }
            if (criteria.getAppName() != null) {
                obj.addLikeClause(VwAppRoles.PROP_APPNAME,
                        criteria.getAppName());
            }
            if (criteria.getRoleId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_ROLEID, criteria.getRoleId());
            }
            if (criteria.getRoleName() != null) {
                obj.addLikeClause(VwAppRoles.PROP_ROLENAME,
                        criteria.getRoleName());
            }
            if (criteria.getAppRoleCode() != null) {
                obj.addLikeClause(VwAppRoles.PROP_APPROLECODE,
                        criteria.getAppRoleCode());
            }
        }
        try {
            roles = this.client.retrieveList(obj);
            if (roles == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwAppRoles item : roles) {
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(item,
                    this.updateUserId);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the vw_user_app_role view for roles assigned to the user
     * specified in <i>criteria</i>.
     * <p>
     * This method basically returns all roles that are assigned to a particular
     * user for a given application.
     * <p>
     * See getAppRoleAssigned method of legacy class ApplicationBeanImpl.java
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing a combination of
     *            properties that are used to build selection criteria:
     *            <ol>
     *            <li>user name (required)</li> <li>login id</li> <li>first name
     *            </li> <li>last name</li> <li>application role id</li> <li>
     *            application id</li> <li>role id</li>
     *            </ol>
     * @return a List of {@link CategoryDto} objects representing the assigned
     *         roles or null if the criteria does not return a data set.
     * @throws SecurityDaoException
     *             When <i>criterai</i> is null, the user's login id is not
     *             included in <i>criterai</i> or database access errors.
     */
    @Override
    public List<CategoryDto> fetchUserAssignedRoles(CategoryDto criteria)
            throws RoleDaoException {
        if (criteria == null) {
            this.msg = "User selection criteria object is invalid";
            throw new RoleDaoException(this.msg);
        }
        VwUserAppRoles obj = new VwUserAppRoles();
        if (criteria.getUsername() == null) {
            this.msg = "The user's login id is required";
            throw new RoleDaoException(this.msg);
        }
        obj.addCriteria(VwUserAppRoles.PROP_USERNAME, criteria.getUsername());

        if (criteria.getLoginUid() > 0) {
            obj.addCriteria(VwUserAppRoles.PROP_LOGINUID,
                    criteria.getLoginUid());
        }
        if (criteria.getFirstname() != null) {
            obj.addCriteria(VwUserAppRoles.PROP_FIRSTNAME,
                    criteria.getFirstname());
        }
        if (criteria.getLastname() != null) {
            obj.addCriteria(VwUserAppRoles.PROP_LASTNAME,
                    criteria.getLastname());
        }
        if (criteria.getAppRoleId() > 0) {
            obj.addCriteria(VwUserAppRoles.PROP_APPROLEID,
                    criteria.getAppRoleId());
        }
        if (criteria.getApplicationId() > 0) {
            obj.addCriteria(VwUserAppRoles.PROP_APPLICATIONID,
                    criteria.getApplicationId());
        }
        if (criteria.getAppName() != null) {
            obj.addCriteria(VwUserAppRoles.PROP_APPNAME, criteria.getAppName());
        }
        if (criteria.getRoleId() > 0) {
            obj.addCriteria(VwUserAppRoles.PROP_ROLEID, criteria.getRoleId());
        }

        StringBuffer customSql = new StringBuffer(100);
        customSql
                .append("app_role_id in (select x.app_role_id from user_app_role x, app_role z ");
        customSql.append("where x.app_role_id = z.app_role_id and ");
        customSql.append("z.app_id = application_id and ");
        customSql.append("x.login_id = login_uid)");
        obj.addCustomCriteria(customSql.toString());
        obj.addOrderBy(VwUserAppRoles.PROP_APPROLENAME,
                VwUserAppRoles.ORDERBY_ASCENDING);

        List<VwUserAppRoles> roles;
        try {
            roles = this.client.retrieveList(obj);
            if (roles == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwUserAppRoles item : roles) {
            CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item,
                    this.updateUserId);
            list.add(dto);
        }
        return list;
    }

    /**
     * Queries the vw_app_role view for roles that have been revoked for the
     * user specified in <i>criteria</i>.
     * <p>
     * This query is drivn by the user's internal unique id.
     * <p>
     * See getAppRoleAssigned method of legacy class ApplicationBeanImpl.java
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing a combination of
     *            property values that are used to build selection criteria:
     *            <ol>
     *            <li>user's internal unique id (required)</li> <li>application
     *            role id</li> <li>application id</li> <li>role id</li>
     *            </ol>
     * @return a List of {@link CategoryDto} objects representing the revoked
     *         roles or null if the criteria does not return a data set.
     * @throws SecurityDaoException
     *             the user's internal unique id is not included in
     *             <i>criterai</i> or database access errors
     */
    @Override
    public List<CategoryDto> fetchUserRevokedRoles(CategoryDto criteria)
            throws RoleDaoException {
        VwAppRoles obj = new VwAppRoles();
        if (criteria != null) {
            if (criteria.getLoginUid() <= 0) {
                this.msg = "The user's internal unique id is required";
                throw new RoleDaoException(this.msg);
            }
            if (criteria.getAppRoleId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_APPROLEID,
                        criteria.getAppRoleId());
            }
            if (criteria.getApplicationId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_APPLICATIONID,
                        criteria.getApplicationId());
            }
            if (criteria.getRoleId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_ROLEID, criteria.getRoleId());
            }
        }
        StringBuffer customSql = new StringBuffer(100);
        customSql
                .append("app_role_id not in (select x.app_role_id from user_app_role x, app_role z ");
        customSql.append("where x.app_role_id = z.app_role_id and ");
        customSql.append("z.app_id = application_id ");
        customSql.append(" and x.login_id = " + criteria.getLoginUid());
        customSql.append(")");

        obj.addCustomCriteria(customSql.toString());
        obj.addOrderBy(VwAppRoles.PROP_APPROLENAME,
                VwAppRoles.ORDERBY_ASCENDING);

        List<VwAppRoles> roles;
        try {
            roles = this.client.retrieveList(obj);
            if (roles == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwAppRoles item : roles) {
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(item,
                    this.updateUserId);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing record in the roles table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param role
     *            an instance of {@link CategoryDto} containing data that will
     *            be mapped to an {@link Roles} instance targeted for a
     *            insert/update operation.
     * @return The id of the new role or the total number of records effected by
     *         an update operation.
     * @throws SecurityDaoException
     */
    @Override
    public int maintainRole(CategoryDto role) throws RoleDaoException {
        if (role == null) {
            throw new RoleDaoException("The Role input parameter is required");
        }
        Roles r = new Roles();
        r.setRoleId(role.getRoleId());
        r.setName(role.getRoleName());
        r.setDescription(role.getRoleDescription());
        r.setUserId(role.getUpdateUserId());

        this.validateRole(r);

        int rc = 0;
        if (role.getRoleId() > 0) {
            rc = this.updateRole(r);
        }
        if (role.getRoleId() == 0) {
            rc = this.createRole(r);
            r.setRoleId(rc);
            role.setRoleId(rc);
        }
        return rc;
    }

    /**
     * Adds an role record to the database,.
     * 
     * @param role
     *            {@link com.bean.Roles Roles}.
     * @throws RoleDaoException
     */
    private int createRole(Roles role) throws RoleDaoException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            role.setDateCreated(ut.getDateCreated());
            role.setDateUpdated(ut.getDateCreated());
            role.setUserId(ut.getLoginId());
            return this.client.insertRow(role, true);
        } catch (DatabaseException e) {
            this.msg = "DatabaseExeception: " + e.getMessage();
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * Updates one an role record in the database.
     * 
     * @param role
     *            {@link com.bean.Roles Roles}.
     * @throws RoleDaoException
     *             for database and system errors.
     */
    private int updateRole(Roles role) throws RoleDaoException {
        try {
            // Get original record
            Roles origRole = new Roles();
            origRole.addCriteria(Roles.PROP_ROLEID, role.getRoleId());
            origRole = (Roles) this.client.retrieveObject(origRole);

            // Update record
            role.addCriteria(Roles.PROP_ROLEID, role.getRoleId());
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            role.setDateCreated(origRole.getDateCreated());
            role.setDateUpdated(ut.getDateCreated());
            role.setUserId(ut.getLoginId());
            return this.client.updateRow(role);
        } catch (DatabaseException e) {
            this.msg = "DatabaseExeception: " + e.getMessage();
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * This method is responsble for validating a role profile. The name and
     * description of the application are to have values.
     * 
     * @param app
     *            {@link com.bean.Application Application}
     * @throws RoleDaoException
     */
    private void validateRole(Roles role) throws RoleDaoException {
        if (role.getName() == null || role.getName().length() <= 0) {
            this.msg = "Role name cannot be blank";
            throw new RoleDaoException(this.msg);
        }

        if (role.getDescription() == null
                || role.getDescription().length() <= 0) {
            this.msg = "Role description cannot be blank";
            throw new RoleDaoException(this.msg);
        }
    }

    /**
     * Deletes a record from the Roles table using the specified primary key,
     * <i>roleId</i>.
     * 
     * @param roleId
     *            The id of the role that is to be delete.
     * @return The total number of targets affected.
     * @throws SecurityDaoException
     */
    @Override
    public int deleteRole(int roleId) throws RoleDaoException {
        if (roleId <= 0) {
            this.msg = "Role id is invalid: " + roleId;
            throw new RoleDaoException(this.msg);
        }
        try {
            Roles role = new Roles();
            role.addCriteria(Roles.PROP_ROLEID, roleId);
            return this.client.deleteRow(role);
        } catch (DatabaseException e) {
            this.msg = "A database error occurred during Role delete";
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * Creates a new or modifies an existing record in the app_role table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param appRole
     *            an instance of {@link CategoryDto} containing data that will
     *            be mapped to an {@link AppRole} instance targeted for a
     *            insert/update operation.
     * @return The unique identifier of the application role for new records or
     *         the total number of rows effected for the modification of
     *         existing records.
     * @throws SecurityDaoException
     */
    @Override
    public int maintainAppRole(CategoryDto appRole) throws RoleDaoException {
        if (appRole == null) {
            throw new RoleDaoException(
                    "The application role input parameter is required");
        }
        AppRole ar = new AppRole();
        ar.setAppRoleId(appRole.getAppRoleId());
        ar.setAppId(appRole.getApplicationId());
        ar.setRoleId(appRole.getRoleId());
        ar.setCode(appRole.getAppRoleCode());
        ar.setName(appRole.getAppRoleName());
        ar.setDescription(appRole.getAppRoleDescription());
        ar.setUserId(appRole.getUpdateUserId());

        this.validateAppRole(ar);

        int rc = 0;
        if (ar.getAppRoleId() > 0) {
            rc = this.updateAppRole(ar);
        }
        if (ar.getAppRoleId() == 0) {
            rc = this.createAppRole(ar);
            appRole.setAppRoleId(rc);
            ar.setAppRoleId(rc);
        }
        return rc;
    }

    /**
     * This method is responsble for validating an application profile. The
     * name, description, code, application id, and role id must contain valid
     * values.
     * 
     * @param app
     *            an instance of {@link AppRole}
     * @throws RoleDaoException
     */
    private void validateAppRole(AppRole appRole) throws RoleDaoException {
        if (appRole.getAppId() <= 0) {
            this.msg = "Application Id is invalid";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getRoleId() <= 0) {
            this.msg = "Role Id is invalid";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getCode() == null || appRole.getCode().length() <= 0) {
            this.msg = "Code cannot be blank";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getName() == null || appRole.getName().length() <= 0) {
            this.msg = "Name cannot be blank";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getDescription() == null
                || appRole.getDescription().length() <= 0) {
            this.msg = "Description cannot be blank";
            throw new RoleDaoException(this.msg);
        }
    }

    /**
     * Adds an application role record to the database,.
     * 
     * @param appRole
     *            an instance of {@link AppRole}
     * @throws RoleDaoException
     */
    private int createAppRole(AppRole appRole) throws RoleDaoException {

        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            appRole.setDateCreated(ut.getDateCreated());
            appRole.setDateUpdated(ut.getDateCreated());
            appRole.setUserId(ut.getLoginId());
            return this.client.insertRow(appRole, true);
        } catch (DatabaseException e) {
            this.msg = "A database error prevented the creation of an Application Role";
            throw new RoleDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "A system error prevented the creation of an Application Role";
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * Updates one an application record in the database.
     * 
     * @param appRole
     *            An instance of {@link AppRole}
     * @throws RoleDaoException
     *             for database and system errors.
     */
    private int updateAppRole(AppRole appRole) throws RoleDaoException {
        try {
            // Get original record
            AppRole origRec = new AppRole();
            origRec.addCriteria(AppRole.PROP_APPROLEID, appRole.getAppRoleId());
            origRec = (AppRole) this.client.retrieveObject(origRec);

            // Update application role
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            appRole.setDateCreated(origRec.getDateCreated());
            appRole.setDateUpdated(ut.getDateCreated());
            appRole.setUserId(ut.getLoginId());
            appRole.addCriteria(AppRole.PROP_APPROLEID, appRole.getAppRoleId());
            return this.client.updateRow(appRole);
        } catch (DatabaseException e) {
            this.msg = "A database error prevented the update of an Application Role";
            throw new RoleDaoException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "A system error prevented the update of an Application Role";
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * Deletes a record from the app_role table using the specified primary key,
     * <i>appRoleId</i>.
     * 
     * @param appRoleId
     *            The uniqe identifier of the application role.
     * @return The number of targets effected by the transaction.
     * @throws SecurityDaoException
     */
    @Override
    public int deleteAppRole(int appRoleId) throws RoleDaoException {
        if (appRoleId <= 0) {
            this.msg = "Application Role id is invalid: " + appRoleId;
            throw new RoleDaoException(this.msg);
        }
        try {
            AppRole ar = new AppRole();
            ar.addCriteria(AppRole.PROP_APPROLEID, appRoleId);
            return this.client.deleteRow(ar);
        } catch (DatabaseException e) {
            this.msg = "A database error occurred during Application Role delete";
            throw new RoleDaoException(this.msg, e);
        }
    }

    /**
     * Refreshes the user_app_role table with new data for the specified user
     * and an application containted in <i>userAppRole</i>.
     * <p>
     * This method will basically assign to and revoke roles from a user.
     * 
     * @param userAppRole
     *            an instance of {@link CategoryDto} containing the login id and
     *            the application id.
     * @param assignedRoles
     *            A String array of assigned application-role codes.
     * @param revokedRoles
     *            A String array of revoked application-role codes.
     * @return Total number of rows effected.
     * @throws SecurityDaoException
     */
    @Override
    public int maintainUserAppRole(CategoryDto userAppRole,
            String[] assignedRoles, String[] revokedRoles)
            throws RoleDaoException {
        String userName = userAppRole.getUsername();
        int appId = userAppRole.getApplicationId();

        int rows;
        UserLogin user = new UserLogin();

        // Verify login id.
        user.addCriteria(UserLogin.PROP_USERNAME, userName);
        try {
            user = (UserLogin) this.client.retrieveObject(user);
            if (user == null) {
                throw new RoleDaoException(
                        "User Application Role update failed due to user login is invalid");
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        // Get all roles belonging to a particular application
        String roles[] = null;
        try {
            AppRole ar = new AppRole();
            ar.addCriteria(AppRole.PROP_APPID, appId);
            List list = this.client.retrieveList(ar);
            if (list != null || list.size() > 0) {
                roles = new String[list.size()];
                int ndx = 0;
                for (Object obj : list) {
                    roles[ndx++] = String.valueOf(((AppRole) obj)
                            .getAppRoleId());
                }
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        // Delete all roles assoicated with a particular application for the
        // user.
        this.deleteUserAppRoles(userName, assignedRoles);

        // Verify that we have a valid list of assigned roles. An invalid list
        // means
        // that all user roles were revoked and there are no new roles to
        // assign.
        if (assignedRoles == null) {
            return 0;
        }

        // Use the list of application role code names to build an array of
        // equivalent role id's
        String roleIdArray[] = this.getAppRoleIdList(assignedRoles);

        // Add each user role based on the new list of assigned roles.
        rows = 0;
        for (String roleId : roleIdArray) {
            try {
                UserAppRole uar = new UserAppRole();
                uar.setLoginId(user.getLoginId());
                uar.setAppRoleId(Integer.parseInt(roleId));
                UserTimestamp ut = RMT2Date.getUserTimeStamp(userAppRole
                        .getUpdateUserId());
                uar.setDateCreated(ut.getDateCreated());
                uar.setDateUpdated(ut.getDateCreated());
                uar.setUserId(ut.getLoginId());
                rows += this.client.insertRow(uar, true);
            } catch (DatabaseException e) {
                this.msg = "DatabaseException: " + e.getMessage();
                throw new RoleDaoException(this.msg);
            } catch (SystemException e) {
                this.msg = "SystemException: " + e.getMessage();
                throw new RoleDaoException(this.msg);
            }
        }
        return rows;
    }

    private String[] getAppRoleIdList(String roles[]) throws RoleDaoException {
        AppRole obj = new AppRole();
        obj.addInClause(AppRole.PROP_CODE, roles);
        List<AppRole> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (roles == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        int cnt = 0;
        String roleId[] = new String[results.size()];
        for (AppRole item : results) {
            roleId[cnt++] = String.valueOf(item.getAppRoleId());
        }
        return roleId;
    }

    /**
     * Delete all records from the user_app_role table that are assoicated with
     * a particular user.
     * <p>
     * Each role contained in <i>appRoles</i> will be used as selection criteria
     * for the delete operation.
     * 
     * @param userName
     *            The login id of the user to associate roles
     * @param appRoles
     *            A list of user application roles used as selection criteria to
     *            perform the delete.
     * @return The total number of records effected
     * @throws SecurityDaoException
     */
    @Override
    public int deleteUserAppRoles(String userName, String[] appRoles)
            throws RoleDaoException {
        UserLogin user = new UserLogin();

        // Verify login id.
        user.addCriteria(UserLogin.PROP_USERNAME, userName);
        try {
            user = (UserLogin) this.client.retrieveObject(user);
            if (user == null) {
                throw new RoleDaoException(
                        "User Application Role delete failed due to inability to obtain user profile");
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        // Use the list of application role code names to build an array of
        // equivalent role id's
        String roleIdArray[] = this.getAppRoleIdList(appRoles);

        try {
            UserAppRole uar = new UserAppRole();
            uar.addCriteria(UserAppRole.PROP_LOGINID, user.getLoginId());
            // Consider role condition only if one or more application roles
            // were found above.
            if (appRoles != null && appRoles.length > 0) {
                uar.addInClause(UserAppRole.PROP_APPROLEID, roleIdArray);
            }
            int rows = this.client.deleteRow(uar);
            logger.info("Total number of user application-roles deleted for user, "
                    + userName + ": " + rows);
            return rows;
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#getUserApps(java.lang.String)
     */
    @Override
    public List<CategoryDto> fetchUserAppRole(String userName)
            throws RoleDaoException {
        VwUserAppRoles ua = new VwUserAppRoles();
        ua.addCriteria(VwUserAppRoles.PROP_USERNAME, userName);
        List<VwUserAppRoles> apps = null;
        try {
            apps = this.client.retrieveList(ua);
            if (apps == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwUserAppRoles item : apps) {
            CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item,
                    this.updateUserId);
            list.add(dto);
        }
        return list;
    }

    /**
     * Not supported.
     */
    @Override
    public CategoryDto fetchRole(String roleName) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported.
     */
    @Override
    public int deleteRole(String roleName) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#deleteAppRole(java.lang.String)
     */
    @Override
    public int deleteAppRole(String appRoleCode) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
