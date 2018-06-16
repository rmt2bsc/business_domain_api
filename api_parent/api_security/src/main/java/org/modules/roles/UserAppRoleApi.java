package org.modules.roles;

import java.util.List;

import org.dto.CategoryDto;
import org.modules.CategoryApiModule;
import org.modules.SecurityModuleException;

/**
 * Api interface for mananging user application roles.
 * 
 * @author rterrell
 * 
 */
public interface UserAppRoleApi extends CategoryApiModule {
 
    /**
     * Obtains a list of application-roles that are assigned to the user
     * specified in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing the following
     *            optional properties to build selection criteria:
     *            <ol>
     *            <li>login id</li>
     *            <li>user name</li>
     *            <li>first name</li>
     *            <li>last name</li>
     *            <li>application role id</li>
     *            <li>application id</li>
     *            <li>role id</li>
     *            </ol>
     * @return a List of {@link CategoryDto} objects or null if the criteria
     *         does not return a data set.
     * @throws SecurityModuleException
     */
    List<CategoryDto> getAssignedRoles(CategoryDto criteria) throws SecurityModuleException;

    /**
     * Obtains a list of application-roles that have not been assigned (revoked)
     * to the user specified in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing the following
     *            optional properties to build selection criteria:
     *            <ol>
     *            <li>application role id</li>
     *            <li>application id</li>
     *            <li>role id</li>
     *            </ol>
     * @return A List of {@link CategoryDto} objects containing data pertainaing
     *         the revoked application roles.
     * @throws SecurityDaoException
     */
    List<CategoryDto> getRevokedRoles(CategoryDto criteria) throws SecurityModuleException;

    /**
     * Assigns a new list of roles to the user for an a given application.
     * <p>
     * In order to identify the user and application the roles are to be
     * associated, <i>userAppDetails</i> is required to contain the user name
     * and application id. This opertation should perform a complete refresh of
     * the user's application roles. This means that all roles are deleted, and
     * all elements of <i>roles</i> will be assigned.
     * 
     * @param userAppDetails
     *            an instance of {@link CategoryDto} containing the login id and
     *            the application id.
     * @param roleCodes
     *            A List of Strings where each item is an application role code
     *            that is to be assigned.
     * @return Total number of roles assinged
     * @throws SecurityDaoException
     */
    int update(CategoryDto userAppDetails, List<String> roleCodes) throws SecurityModuleException;

    /**
     * Delete the specified roles which belongs to a user of a particular
     * application.
     * 
     * @param userName
     *            The login id of the user to associate roles
     * @param appRoles
     *            A List of Strings representing the roles to removed.
     * @return The total number of roles deleted.
     * @throws SecurityModuleException
     */
    int delete(String userName, List<String> appRoles) throws SecurityModuleException;
}
