package org.dao.roles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.SecurityDaoImpl;
import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.Roles;
import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dto.CategoryDto;
import org.dto.UserDto;
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
    private static final Logger logger = Logger.getLogger(Rmt2OrmRoleDaoImpl.class);

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

    @Override
    public List<CategoryDto> fetchRole(CategoryDto criteria) throws SecurityDaoException {
        Roles roles = RoleDaoFactory.createCriteriaRoles(criteria);
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
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(item, this.updateUserId);
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
    public List<CategoryDto> fetchAppRole(CategoryDto criteria) throws RoleDaoException {
        VwAppRoles obj = RoleDaoFactory.createCriteriaVwAppRoles(criteria);
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
            CategoryDto dto = Rmt2OrmDtoFactory.getAppRoleDtoInstance(item, this.updateUserId);
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
    public List<CategoryDto> fetchUserAssignedRoles(CategoryDto criteria) throws RoleDaoException {
        VwUserAppRoles obj = RoleDaoFactory.createCriteriaVwUserAppRoles(criteria);

        StringBuffer customSql = new StringBuffer(100);
        customSql.append("app_role_id in (select x.app_role_id from user_app_role x, app_role z ");
        customSql.append("where x.app_role_id = z.app_role_id and ");
        customSql.append("z.app_id = application_id and ");
        customSql.append("x.login_id = login_uid)");
        obj.addCustomCriteria(customSql.toString());
        obj.addOrderBy(VwUserAppRoles.PROP_APPROLENAME, VwUserAppRoles.ORDERBY_ASCENDING);

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
            CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item, this.updateUserId);
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
    public List<CategoryDto> fetchUserRevokedRoles(CategoryDto criteria) throws RoleDaoException {
        VwAppRoles obj = RoleDaoFactory.createCriteriaVwAppRoles(criteria);

        StringBuffer customSql = new StringBuffer(100);
        customSql.append("app_role_id not in (select x.app_role_id from user_app_role x, app_role z ");
        customSql.append("where x.app_role_id = z.app_role_id and ");
        customSql.append("z.app_id = application_id ");
        customSql.append(" and x.login_id = " + criteria.getLoginUid());
        customSql.append(")");

        obj.addCustomCriteria(customSql.toString());
        obj.addOrderBy(VwAppRoles.PROP_APPROLENAME, VwAppRoles.ORDERBY_ASCENDING);

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
        Roles r = new Roles();
        r.setRoleId(role.getRoleId());
        r.setName(role.getRoleName());
        r.setDescription(role.getRoleDescription());
        r.setUserId(role.getUpdateUserId());

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
        AppRole ar = new AppRole();
        ar.setAppRoleId(appRole.getAppRoleId());
        ar.setAppId(appRole.getApplicationId());
        ar.setRoleId(appRole.getRoleId());
        ar.setCode(appRole.getAppRoleCode());
        ar.setName(appRole.getAppRoleName());
        ar.setDescription(appRole.getAppRoleDescription());
        ar.setUserId(appRole.getUpdateUserId());

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
     * @param assignedRoleId
     *            A String array of assigned application-role id's.
     * @param revokedRoles
     *            A String array of revoked application-role codes.
     * @return Total number of rows effected.
     * @throws SecurityDaoException
     */
    @Override
    public int maintainUserAppRole(UserDto user, String[] assignedRoleId, String[] revokedRoles)
            throws RoleDaoException {

        // Add each user role based on the new list of assigned roles.
        int rows = 0;
        for (String roleId : assignedRoleId) {
            try {
                UserAppRole uar = new UserAppRole();
                uar.setLoginId(user.getLoginUid());
                uar.setAppRoleId(Integer.parseInt(roleId));
                UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
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

    @Override
    public String[] fetchAppRoleIdList(String roles[]) throws RoleDaoException {
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
    public int deleteUserAppRoles(UserDto user, String[] appRoleId) throws RoleDaoException {
        try {
            UserAppRole uar = new UserAppRole();
            uar.addCriteria(UserAppRole.PROP_LOGINID, user.getLoginUid());
            // Consider role condition only if one or more application roles
            // were found above.
            if (appRoleId != null && appRoleId.length > 0) {
                uar.addInClause(UserAppRole.PROP_APPROLEID, appRoleId);
            }
            int rows = this.client.deleteRow(uar);
            logger.info("Total number of user application-roles deleted for user, " + user + ": " + rows);
            return rows;
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }
    }

    /**
     * Fethes a list of user application roles based on selection criteria.
     * <p>
     * The data source is the <i>VwUserAppRoles</i> table.
     * 
     * @param criteria
     *            an instance of {@link CategoryDto}
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityDaoException
     */
    @Override
    public List<CategoryDto> fetchUserAppRole(CategoryDto criteria) throws SecurityDaoException {
        VwUserAppRoles obj = RoleDaoFactory.createCriteriaVwUserAppRoles(criteria);
        List<VwUserAppRoles> apps = null;
        try {
            apps = this.client.retrieveList(obj);
            if (apps == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (VwUserAppRoles item : apps) {
            CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item, this.updateUserId);
            list.add(dto);
        }
        return list;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#getUserApps(java.lang.String)
     */
    @Override
    public List<CategoryDto> fetchUserAppRole(String userName) throws RoleDaoException {
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
            CategoryDto dto = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(item, this.updateUserId);
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
