package org.modules.roles;

import java.util.List;

import org.dto.CategoryDto;
import org.modules.CategoryApiModule;
import org.modules.SecurityModuleException;

/**
 * Api interface for mananging application roles.
 * 
 * @author rterrell
 * 
 */
public interface AppRoleApi extends CategoryApiModule {

    // /**
    // * Obtains a list of application roles related to a user.
    // *
    // * @param userName
    // * the login id of user.
    // * @return
    // * List of {@link CategoryDto} objects containing application role data.
    // * @throws SecurityModuleException
    // */
    // List<CategoryDto> get(String userName) throws SecurityModuleException;
    //
    // /**
    // * Obtains a list of application roles related to a user and an
    // application.
    // *
    // * @param userName
    // * the login id of user.
    // * @param appName
    // * the name/code of the application
    // * @return
    // * List of {@link CategoryDto} objects containing application role data.
    // * @throws SecurityModuleException
    // */
    // List<CategoryDto> get(String userName, String appName) throws
    // SecurityModuleException;

    /**
     * Obtains a list of application roles based on various selection properties
     * contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link CategoryDto} containing various property
     *            values to build selection criteria.
     * @return List of {@link CategoryDto} objects containing application role
     *         data.
     * @throws SecurityModuleException
     */
    List<CategoryDto> get(CategoryDto criteria) throws SecurityModuleException;

    /**
     * Delete an API record from a data source by its application role code
     * 
     * @param appRoleCode
     *            The application role code
     * @return the total number of records deleted.
     * @throws SecurityModuleException
     */
    int delete(String appRoleCode) throws SecurityModuleException;

}
