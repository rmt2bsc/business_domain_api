package org.modules.roles;

import org.dto.CategoryDto;
import org.modules.CategoryApiModule;
import org.modules.SecurityModuleException;

/**
 * Contract for the Role module of the Security API
 * 
 * @author rterrell
 * 
 */
public interface RoleApi extends CategoryApiModule {

    /**
     * Retrieve an API record using its role name
     * 
     * @param roleName
     *            The role name
     * @return An instance of {@link CategoryDto}
     * @throws SecurityModuleException
     */
    CategoryDto get(String roleName) throws SecurityModuleException;

    /**
     * Delete a role by its name
     * 
     * @param roleName
     *            The role name
     * @return the total number of records deleted.
     * @throws SecurityModuleException
     */
    int delete(String roleName) throws SecurityModuleException;

}
