package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.Application;
import org.dao.mapping.orm.rmt2.Roles;
import org.dao.mapping.orm.rmt2.UserGroup;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.UserResource;
import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dao.mapping.orm.rmt2.VwResource;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dao.mapping.orm.rmt2.VwUserGroup;
import org.dto.ApplicationDto;
import org.dto.CategoryDto;
import org.dto.ResourceDto;
import org.dto.UserDto;
import org.dto.WebServiceDto;

import com.RMT2Base;

import com.api.security.User;

/**
 * A factory containing several adapters which function to create Security API
 * specific entity beans.
 * 
 * @author rterrell
 * 
 */
public class Rmt2OrmDtoFactory extends RMT2Base {

    /**
     * Creates a instance of class <i>ApplicationDto</i> based on
     * <i>Application</i> instance.
     * 
     * @return an instance of {@link ApplicationDto}.
     */
    public static final ApplicationDto getNewAppCategoryInstance() {
        Application app = new Application();
        return Rmt2OrmDtoFactory.getAppDtoInstance(app);
    }

    /**
     * Creates a CategoryDto instance from an <i>Roles</i> object.
     * 
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getNewRoleCategoryInstance() {
        Roles role = new Roles();
        CategoryDto dto = new RolesRmt2OrmAdapter(role);
        return dto;
    }

    /**
     * Creates a instance of class <i>CategoryDto</i> based on
     * <i>Application</i> instance.
     * 
     * @return an instance of {@link CategoryDto}.
     */
    public static final CategoryDto getNewAppRoleCategoryInstance() {
        AppRole appRole = new AppRole();
        return Rmt2OrmDtoFactory.getAppRoleDtoInstance(appRole);
    }

    /**
     * Creates a instance of class <i>CategoryDto</i> based on an instance of
     * <i>VwAppRoles</i>.
     * 
     * @param UpdateUserName
     *            The id of the user manipulating this DTO.
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getNewExtAppRoleCategoryInstance(
            String UpdateUserName) {
        VwAppRoles ar = new VwAppRoles();
        return Rmt2OrmDtoFactory.getAppRoleDtoInstance(ar, UpdateUserName);
    }

    /**
     * Creates a instance of class <i>CategoryDto</i> based on an instance of
     * <i>VwUserAppRoles</i>.
     * 
     * @param UpdateUserName
     *            The id of the user manipulating this DTO.
     * @return an instance of {@link CategoryDto}
     */
    public static final CategoryDto getNewExtUserAppRoleCategoryInstance(
            String UpdateUserName) {
        VwUserAppRoles ar = new VwUserAppRoles();
        return Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(ar, UpdateUserName);
    }

    /**
     * Creates a instance of class <i>UserDto</i> based on an instance of
     * <i>UserLogin</i>.
     * 
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getNewUserInstance() {
        UserLogin u = new UserLogin();
        return Rmt2OrmDtoFactory.getUserDtoInstance(u);
    }

    /**
     * Creates a instance of class <i>UserDto</i> based on an instance of
     * <i>VwUser</i>.
     * 
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getNewExtUserInstance() {
        VwUser u = new VwUser();
        return Rmt2OrmDtoFactory.getUserDtoInstance(u);
    }

    /**
     * Creates a instance of class <i>UserDto</i> based on an instance of
     * <i>VwUserGroup</i>.
     * 
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getNewUserGroupInstance() {
        VwUserGroup u = new VwUserGroup();
        return Rmt2OrmDtoFactory.getUserDtoInstance(u);
    }

    /**
     * Creates a instance of class <i>UserDto</i> based on an instance of
     * <i>UserGroup</i>.
     * 
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getNewGroupInstance() {
        UserGroup u = new UserGroup();
        return Rmt2OrmDtoFactory.getGroupDtoInstance(u);
    }

    /**
     * Creates a instance of class <i>WebServiceDto</i> based on an instance of
     * <i>UserResource</i>.
     * 
     * @return an instance of {@link WebServiceDto}
     */
    public static final WebServiceDto getNewResourceInstance() {
        UserResource obj = new UserResource();
        return Rmt2OrmDtoFactory.getResourceDtoInstance(obj);
    }

    /**
     * Creates a instance of class <i>ResourceDto</i> based on an instance of
     * <i>UserResourceType</i>.
     * 
     * @return an instance of {@link ResourceDto}
     */
    public static final ResourceDto getNewResourceTypeInstance() {
        UserResourceType obj = new UserResourceType();
        return Rmt2OrmDtoFactory.getResourceDtoInstance(obj);
    }

    /**
     * Creates a instance of class <i>ResourceDto</i> based on an instance of
     * <i>UserResourceSubtype</i>.
     * 
     * @return an instance of {@link ResourceDto}
     */
    public static final ResourceDto getNewResourceSubTypeInstance() {
        UserResourceSubtype obj = new UserResourceSubtype();
        return Rmt2OrmDtoFactory.getResourceDtoInstance(obj);
    }

    /**
     * Creates a instance of class <i>WebServiceDto</i> based on an instance of
     * <i>VwResource</i>.
     * 
     * @return an instance of {@link WebServiceDto}
     */
    public static final WebServiceDto getNewExtResourceInstance() {
        VwResource obj = new VwResource();
        return Rmt2OrmDtoFactory.getResourceDtoInstance(obj);
    }

    /**
     * Creates a User instance from UserDto object.
     * 
     * @param ul
     *            an instance of {@link UserDto}
     * @return an instance of {@link User}
     */
    public static final User getUserInstance(UserDto ul) {
        User user = new UserAdapter(ul);
        return user;
    }

    /**
     * Creates a User instance from UserLogin object.
     * 
     * @param ul
     *            an instance of {@link UserLogin}
     * @return an instance of {@link User}
     */
    public static final User getUserInstance(UserLogin ul) {
        User user = new UserRmt2OrmAdapter(ul);
        return user;
    }

    /**
     * Creates a UserDto instance from UserLogin object.
     * 
     * @param ul
     *            an instance of {@link UserLogin}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getUserDtoInstance(UserLogin ul) {
        UserDto user = new UserRmt2OrmAdapter(ul);
        return user;
    }

    /**
     * Creates a UserDto instance from VwUser object.
     * 
     * @param u
     *            an instance of {@link VwUser}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getUserDtoInstance(VwUser u) {
        UserDto user = new UserExtRmt2OrmAdapter(u);
        return user;
    }

    /**
     * Creates a UserDto instance from VwUserGroup object.
     * 
     * @param u
     *            an instance of {@link VwUserGroup}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getUserDtoInstance(VwUserGroup u) {
        UserDto user = new UserGroupRmt2OrmAdapter(u);
        return user;
    }

    /**
     * Creates a UserDto instance from UserGroup object.
     * 
     * @param u
     *            an instance of {@link UserGroup}
     * @return an instance of {@link UserDto}
     */
    public static final UserDto getGroupDtoInstance(UserGroup u) {
        UserDto user = new GroupRmt2OrmAdapter(u);
        return user;
    }

    /**
     * Creates a ApplicationDto instance from an Application object.
     * 
     * @param app
     *            an instance of {@link Application}
     * @return an instance of {@link ApplicationDto}
     */
    public static final ApplicationDto getAppDtoInstance(Application app) {
        ApplicationDto dto = new ApplicationRmt2OrmAdapter(app);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from an Role object.
     * 
     * @param role
     *            an instance of {@link Roles}
     * @return an isntance of {@link CategoryDto}
     */
    public static final CategoryDto getRoleDtoInstance(Roles role) {
        CategoryDto dto = new RolesRmt2OrmAdapter(role);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from an <i>AppRole</i> object.
     * 
     * @param appRole
     *            an instance of {@link AppRole}
     * @return an isntance of {@link CategoryDto}
     */
    public static final CategoryDto getAppRoleDtoInstance(AppRole appRole) {
        CategoryDto dto = new AppRoleRmt2OrmAdapter(appRole);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from an <i>VwAppRoles</i> object and the
     * login id of the user accessing this adapter.
     * 
     * @param appRole
     *            an instance of {@link VwAppRoles}
     * @param userName
     *            The login id of the user accessign this adapter
     * @return an isntance of {@link CategoryDto}
     */
    public static final CategoryDto getAppRoleDtoInstance(VwAppRoles appRole,
            String userName) {
        CategoryDto dto = new AppRoleExtRmt2OrmAdapter(appRole, userName);
        return dto;
    }

    /**
     * Creates a CategoryDto instance from an VwUserAppRoles object.
     * 
     * @param userAppRole
     *            an instance of {@link VwUserAppRoles}
     * @param updateUserId
     *            the id of the user accessing the DTO
     * @return an isntance of {@link CategoryDto}
     */
    public static final CategoryDto getUserAppRoleDtoInstance(
            VwUserAppRoles userAppRole, String updateUserId) {
        CategoryDto dto = new UserAppRoleExtRmt2OrmAdapter(userAppRole,
                updateUserId);
        return dto;
    }

    /**
     * Creates a WebServiceDto instance from an UserResource object.
     * 
     * @param rsrc
     *            an instance of {@link UserResource}
     * @return an instance of {@link WebServiceDto}
     */
    public static final WebServiceDto getResourceDtoInstance(UserResource rsrc) {
        WebServiceDto dto = new ResourceRmt2OrmAdapter(rsrc);
        return dto;
    }

    /**
     * Creates a ResourceDto instance from an UserResourceType object.
     * 
     * @param rsrcType
     *            an instance of {@link UserResourceType}
     * @return an instance of {@link ResourceDto}
     */
    public static final ResourceDto getResourceDtoInstance(
            UserResourceType rsrcType) {
        ResourceDto dto = new ResourceTypeRmt2OrmAdapter(rsrcType);
        return dto;
    }

    /**
     * Creates a ResourceDto instance from an UserResourceSubtype object.
     * 
     * @param rsrcSubType
     *            an instance of {@link UserResourceSubtype}
     * @return an instance of {@link ResourceDto}
     */
    public static final ResourceDto getResourceDtoInstance(
            UserResourceSubtype rsrcSubType) {
        ResourceDto dto = new ResourceSubTypeRmt2OrmAdapter(rsrcSubType);
        return dto;
    }

    /**
     * Creates a WebServiceDto instance from an VwResource object.
     * 
     * @param rsrcExt
     *            an instance of {@link VwResource}
     * @return an instance of {@link WebServiceDto}
     */
    public static final WebServiceDto getResourceDtoInstance(VwResource rsrcExt) {
        WebServiceDto dto = new ResourceExtRmt2OrmAdapter(rsrcExt);
        return dto;
    }
}
