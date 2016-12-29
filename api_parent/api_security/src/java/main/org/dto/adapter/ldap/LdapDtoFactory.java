package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.mapping.orm.ldap.LdapApplication;
import org.dao.mapping.orm.ldap.LdapComputerApp;
import org.dao.mapping.orm.ldap.LdapResource;
import org.dao.mapping.orm.ldap.LdapRoles;
import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.mapping.orm.ldap.LdapUserGroup;
import org.dao.mapping.orm.ldap.LdapWebService;
import org.dto.ApplicationDto;
import org.dto.CategoryDto;
import org.dto.ComputerAppServerDto;
import org.dto.ResourceDto;
import org.dto.UserDto;
import org.dto.WebServiceDto;

import com.RMT2Base;

import com.api.security.User;

/**
 * A factory containing several adapters which function to create Security API
 * specific entity beans from LDAP data objects.
 * 
 * @author rterrell
 * 
 */
public class LdapDtoFactory extends RMT2Base {

    /**
     * Creates a ApplicationDto instance from LdapApplication object.
     * 
     * @param app
     *            an instance of {@link LdapApplication} or null in order to
     *            create a new ApplicationDto instance.
     * @return an instance of {@link ApplicationDto}
     */
    public static final ApplicationDto getApplicationInstance(
            LdapApplication app) {
        ApplicationDto dto = new ApplicationLdapAdapter(app);
        return dto;
    }

    /**
     * Creates a User instance from UserLogin object.
     * 
     * @param ul
     *            an instance of {@link LdapUser}
     * @return an instance of {@link User}
     */
    public static final User getUserInstance(LdapUser ul) {
        User user = new UserLdapAdapter(ul);
        return user;
    }

    /**
     * Creates a UserDto instance from UserLogin object.
     * 
     * @param ul
     *            an instance of {@link LdapUser}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getUserDtoInstance(LdapUser ul) {
        UserDto user = new UserLdapAdapter(ul);
        return user;
    }

    /**
     * Creates a UserDto instance from LdapUserGroup object.
     * 
     * @param ug
     *            an instance of {@link LdapUserGroup}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getUserDtoInstance(LdapUserGroup ug) {
        UserDto user = new GroupLdapAdapter(ug);
        return user;
    }

    /**
     * Creates a CategoryDto instance from LdapUser object.
     * 
     * @param userRole
     *            an instance of {@link LdapUser}
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getUserAppRoleDtoInstance(LdapUser userRole) {
        CategoryDto dto = new CategoryLdapAdapter(userRole);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from LdapRoles object.
     * 
     * @param role
     *            an instance of {@link LdapRoles}
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getRoleDtoInstance(LdapRoles role) {
        CategoryDto dto = new CategoryLdapAdapter(role);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from LdapAppRoles object.
     * 
     * @param appRole
     *            an instance of {@link LdapAppRoles}
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getApplicationRoleDtoInstance(
            LdapAppRoles appRole) {
        CategoryDto dto = new CategoryLdapAdapter(appRole);
        return dto;
    }

    /**
     * Creates a ResourceDto instance from LdapResource object.
     * 
     * @param resource
     *            an instance of {@link LdapResource}
     * @return an instance of {@link ResourceDto}
     */
    public static final ResourceDto getResourceDtoInstance(LdapResource resource) {
        ResourceDto dto = new ResourceLdapAdapter(resource);
        return dto;
    }

    /**
     * Creates a WebServiceDto instance from LdapWebService object.
     * 
     * @param resource
     *            an instance of {@link LdapWebService}
     * @return an instance of {@link WebServiceDto}
     */
    public static final WebServiceDto getWebServiceResourceDtoInstance(
            LdapWebService resource) {
        WebServiceDto dto = new LdapWebServiceAdapter(resource);
        return dto;
    }

    /**
     * Creates a ComputerAppServerDto instance from LdapComputerApp object.
     * 
     * @param resource
     *            an instance of {@link LdapComputerApp}
     * @return an instance of {@link ComputerAppServerDto}
     */
    public static final ComputerAppServerDto getComputerAppServerDtoInstance(
            LdapComputerApp resource) {
        ComputerAppServerDto dto = new LdapComputerAppServerAdapter(resource);
        return dto;
    }

}
