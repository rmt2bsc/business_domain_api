package org.dao.roles;

import java.util.List;

import org.dao.SecurityDaoException;
import org.dto.CategoryDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data regarding the Role module of the
 * Security API.
 * 
 * @author rterrell
 * 
 */
public interface RoleDao extends DaoClient {

    /**
     * Fetch the master list of roles.
     * <p>
     * The data source is the <i>Roles</i> table.
     * 
     * @return a List of {@link CategoryDto} objects or null if no records are
     *         found.
     * @throws SecurityDaoException
     */
    List<CategoryDto> fetchRole() throws SecurityDaoException;

    /**
     * Fetches a role based on its unique key value.
     * 
     * @param roleId
     *            The id of the role to retrieve.
     * @return An instance of {@link CategoryDto} or null if the record is not
     *         found.
     * @throws SecurityDaoException
     */
    CategoryDto fetchRole(int roleId) throws SecurityDaoException;

    /**
     * Fetches a role based on its unique name.
     * 
     * @param roleName
     *            The name of the role to retrieve.
     * @return An instance of {@link CategoryDto} or null if the record is not
     *         found.
     * @throws SecurityDaoException
     */
    CategoryDto fetchRole(String roleName) throws SecurityDaoException;

    /**
     * Fetches all application roles.
     * <p>
     * The data source is the <i>VwAppRoles</i> table.
     * 
     * @return a List of {@link CategoryDto} objects or null if no records are
     *         found.
     * @throws SecurityDaoException
     */
    List<CategoryDto> fetchAppRole() throws SecurityDaoException;

    /**
     * Fetch a single application role by its unique identifier.
     * <p>
     * The data source is the <i>AppRole</i> table.
     * 
     * @param uid
     *            The unique identifier of the application role.
     * @return an instance of {@link CategoryDto}
     * @throws SecurityDaoException
     */
    CategoryDto fetchAppRole(int uid) throws SecurityDaoException;

    // /**
    // * Fetches all roles belonging to the specified user.
    // * <p>
    // * The data source is the <i>VwUserAppRoles</i> table.
    // *
    // * @param userName
    // * The user's login id.
    // * @return
    // * a List of {@link CategoryDto} objects or null if no records are found.
    // * @throws SecurityDaoException
    // */
    // List<CategoryDto> fetchAppRole(String userName) throws
    // SecurityDaoException;
    //
    // /**
    // * Fetches all application-roles belonging to the specifed user and
    // application code.
    // * <p>
    // * The data source is <i>VwUserAppRoles</i> table.
    // *
    // * @param userName
    // * The user's login id.
    // * @param appName
    // * The name of the related application to retrieve roles.
    // * @return
    // * a List of {@link CategoryDto} objects or null if no records are found.
    // * @throws SecurityDaoException
    // */
    // List<CategoryDto> fetchAppRole(String userName, String appName) throws
    // SecurityDaoException;

    /**
     * Get Application Role data based on information contained in
     * <i>criteria</i>.
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
    List<CategoryDto> fetchAppRole(CategoryDto criteria)
            throws SecurityDaoException;

    /**
     * Fethes a list of applications which the user can access.
     * <p>
     * The data source is the <i>VwUserAppRoles</i> table.
     * 
     * @param userName
     *            User's login id.
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityDaoException
     */
    List<CategoryDto> fetchUserAppRole(String userName)
            throws SecurityDaoException;

    /**
     * Get all roles that are assigned to a particular user for a given
     * application.
     * <p>
     * The data source is the <i>VwUserAppRoles</i> table.
     * <p>
     * See getAppRoleAssigned method of legacy class ApplicationBeanImpl.java
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing a combination of
     *            property values used to build selection criteria:
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityDaoException
     */
    List<CategoryDto> fetchUserAssignedRoles(CategoryDto criteria)
            throws SecurityDaoException;

    /**
     * Get all roles that have been revoked or have not been assigned to a user
     * for a given application.
     * <p>
     * The data source is the <i>VwAppRoles</i> table.
     * 
     * <p>
     * See getAppRoleAssigned method of legacy class ApplicationBeanImpl.java
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing the following
     *            optional properties to build selection criteria:
     *            <ol>
     *            <li>application role id</li> <li>application id</li> <li>role
     *            id</li>
     *            </ol>
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityDaoException
     */
    List<CategoryDto> fetchUserRevokedRoles(CategoryDto criteria)
            throws SecurityDaoException;

    /**
     * Create a new or modify an existing security role.
     * 
     * @param role
     *            An instance of {@link CategoryDto} conatining the data needed
     *            to update the role.
     * @return The id of the new role or the total number of records effected by
     *         an update operation.
     * @throws SecurityDaoException
     */
    int maintainRole(CategoryDto role) throws SecurityDaoException;

    /**
     * Creates a new or modifies an existing application role entity.
     * 
     * @param appRole
     *            an instance of {@link CategoryDto} containing the data needed
     *            to update the application role.
     * @return The unique identifier of the application role for new records or
     *         the total number of rows effected for the modification of
     *         existing records.
     * @throws SecurityDaoException
     */
    int maintainAppRole(CategoryDto appRole) throws SecurityDaoException;

    /**
     * Creates new or modifies existing user application roles.
     * <p>
     * This method will basically assign and/or revoke roles to/from a user.
     * 
     * @param userAppRole
     *            an instance of {@link CategoryDto} containing the login id and
     *            the application id.
     * @param assignedRoles
     *            A String array of assigned application role codes.
     * @param revokedRoles
     *            A String array of revoked application role codes.
     * @return Total number of rows effected.
     * @throws SecurityDaoException
     */
    int maintainUserAppRole(CategoryDto userAppRole, String assignedRoles[],
            String revokedRoles[]) throws SecurityDaoException;

    /**
     * Deletes a security roles from the system.
     * 
     * @param roleId
     *            The id of the role that is to be delete.
     * @return The total number of targets affected.
     * @throws SecurityDaoException
     */
    int deleteRole(int roleId) throws SecurityDaoException;

    /**
     * Deletes a security roles from the system by its name.
     * 
     * @param roleName
     *            The name of the role that is to be delete.
     * @return The total number of targets affected.
     * @throws SecurityDaoException
     */
    int deleteRole(String roleName) throws SecurityDaoException;

    /**
     * Deletes an application role from the system.
     * 
     * @param uid
     *            The uniqe identifier of the application role.
     * @return The number of targets effected by the transaction.
     * @throws SecurityDaoException
     */
    int deleteAppRole(int uid) throws SecurityDaoException;

    /**
     * Deletes an application role from the system using application role code.
     * 
     * @param appRoleCode
     *            The application role code
     * @return The number of targets effected by the transaction.
     * @throws SecurityDaoException
     */
    int deleteAppRole(String appRoleCode) throws SecurityDaoException;

    /**
     * Delete all roles assoicated with a particular user.
     * 
     * @param userName
     *            The login id of the user to associate roles
     * @param appRoles
     *            A list of user application role codes to delete
     * @return The total number of records effected
     * @throws SecurityDaoException
     */
    int deleteUserAppRoles(String userName, String appRoles[])
            throws SecurityDaoException;

}