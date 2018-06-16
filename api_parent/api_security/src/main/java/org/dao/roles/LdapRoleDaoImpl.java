package org.dao.roles;

import java.util.ArrayList;
import java.util.List;

import org.dao.SecurityDaoException;
import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.mapping.orm.ldap.LdapRoles;
import org.dao.mapping.orm.ldap.LdapUser;
import org.dao.user.UserDao;
import org.dao.user.UserDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.NotFoundException;
import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.LdapClient;
import com.api.ldap.operation.LdapAddOperation;
import com.api.ldap.operation.LdapDeleteOperation;
import com.api.ldap.operation.LdapModifyOperation;
import com.api.ldap.operation.LdapSearchOperation;
import com.api.persistence.DatabaseException;

/**
 * JNDI implementation for the {@link RoleDao} interface which accesses and
 * manipulates role related data from a LDAP server.
 * <p>
 * The base DN's in which this implementation operates from for Roles and
 * Application Roles are <i>ou=Roles,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>
 * and <i>ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>, respectively.
 * 
 * @author Roy Terrell
 * 
 */
class LdapRoleDaoImpl extends AbstractLdapDaoClient implements RoleDao {

    /**
     * 
     */
    public LdapRoleDaoImpl() {
        super();
    }

    /**
     * Fetches all roles from DN,
     * <i>ou=Roles,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * The result set is ordered by role name in ascendng order
     * 
     * @return A List of {@link CategoryDto} containing role data.
     * @throws RoleDaoException
     *             General LDAP server access errors
     */
    @Override
    public List<CategoryDto> fetchRole() throws SecurityDaoException {
        List<LdapRoles> roleList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=Roles,ou=LookupCodes");
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapRoles");
            Object results[] = this.ldap.retrieve(op);
            roleList = this.ldap.extractLdapResults(results);
            if (roleList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (LdapRoles item : roleList) {
            CategoryDto dto = LdapDtoFactory.getRoleDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#fetchRole(java.lang.String)
     */
    @Override
    public CategoryDto fetchRole(String roleName) throws SecurityDaoException {
        List<LdapRoles> roleList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=Roles,ou=LookupCodes");
            op.getMatchAttributes().put("cn", roleName);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapRoles");
            Object results[] = this.ldap.retrieve(op);
            roleList = this.ldap.extractLdapResults(results);
            if (roleList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new RoleDaoException(e);
        }

        LdapRoles ldapRole = null;
        if (roleList.size() > 1) {
            this.msg = "Too many roles were returned from LDAP server for a single role query";
            throw new RoleDaoException();
        }
        else {
            ldapRole = roleList.get(0);
        }
        CategoryDto dto = LdapDtoFactory.getRoleDtoInstance(ldapRole);
        return dto;
    }

    /**
     * Fetches all application roles at DN,
     * <i>ou=xxxxx,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @return a List of {@link CategoryDto} objects or null if no records are
     *         found.
     * @throws SecurityDaoException
     */
    @Override
    public List<CategoryDto> fetchAppRole() throws SecurityDaoException {
        return this.fetchAppRole(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#fetchAppRole(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> fetchAppRole(CategoryDto criteria)
            throws SecurityDaoException {
        List<LdapAppRoles> roleList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn("ou=AppRole,ou=LookupCodes");

            if (criteria != null) {
                if (criteria.getAppName() != null) {
                    op.getMatchAttributes().put("appId", criteria.getAppName());
                }
                if (criteria.getAppRoleName() != null) {
                    op.getMatchAttributes().put("appRoleName",
                            criteria.getAppRoleName());
                }
                if (criteria.getAppRoleCode() != null) {
                    op.getMatchAttributes()
                            .put("cn", criteria.getAppRoleCode());
                }
                if (criteria.getRoleName() != null) {
                    op.getMatchAttributes().put("roleId",
                            criteria.getRoleName());
                }
            }

            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapAppRoles");
            Object results[] = this.ldap.retrieve(op);
            roleList = this.ldap.extractLdapResults(results);
            if (roleList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new RoleDaoException(e);
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for (LdapAppRoles item : roleList) {
            CategoryDto dto = LdapDtoFactory
                    .getApplicationRoleDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#fetchUserAppRole(java.lang.String)
     */
    @Override
    public List<CategoryDto> fetchUserAppRole(String userName)
            throws SecurityDaoException {
        LdapUser dummy = null;
        CategoryDto criteria = LdapDtoFactory.getUserAppRoleDtoInstance(dummy);
        criteria.setUsername(userName);
        return this.fetchUserAssignedRoles(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#fetchUserAssignedRoles(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> fetchUserAssignedRoles(CategoryDto criteria)
            throws SecurityDaoException {
        if (criteria == null) {
            this.msg = "User selection criteria object is invalid";
            throw new RoleDaoException(this.msg);
        }

        // Get user object
        if (criteria.getUsername() == null) {
            this.msg = "The user's login id is required";
            throw new RoleDaoException(this.msg);
        }
        UserDaoFactory uf = new UserDaoFactory();
        UserDao uDao = uf.createLdapDao();
        UserDto userDto = null;
        List<UserDto> userList = null;
        try {
            UserDto userCriteria = Rmt2OrmDtoFactory.getNewUserInstance();
            userCriteria.setUsername(criteria.getUsername());
            userList = uDao.fetchUserProfile(userCriteria);
            if (userList != null && userList.size() == 1) {
                userDto = userList.get(0);
            }
        } catch (Exception e) {
            this.msg = "Error obtaining user profile from the LDAP server";
            throw new RoleDaoException(this.msg);
        }
        if (userDto == null) {
            this.msg = "User profile is not found";
            throw new NotFoundException(this.msg);
        }
        // Return null if user does not possess any roles.
        if (userDto.getRoles() == null || userDto.getRoles().size() <= 0) {
            return null;
        }

        // Get application roles from the AppRole DN based on criterai, if any.
        // cn=acctadmin,ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net
        List<CategoryDto> appRoleList = this.fetchAppRole(criteria);
        // Return null if criteria did not yield any matches.
        if (appRoleList == null) {
            return null;
        }

        List<CategoryDto> grantedList = new ArrayList<CategoryDto>();
        for (CategoryDto appRole : appRoleList) {
            for (String userRole : userDto.getRoles()) {
                if (userRole.equalsIgnoreCase(appRole.getAppRoleCode())) {
                    grantedList.add(appRole);
                }
            }
        }
        return grantedList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#fetchUserRevokedRoles(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> fetchUserRevokedRoles(CategoryDto criteria)
            throws SecurityDaoException {
        if (criteria == null) {
            this.msg = "User selection criteria object is invalid";
            throw new RoleDaoException(this.msg);
        }

        // Get user object
        if (criteria.getUsername() == null) {
            this.msg = "The user's login id is required";
            throw new RoleDaoException(this.msg);
        }
        UserDto userDto = this.getUserByUsername(criteria.getUsername());
        if (userDto == null) {
            this.msg = "User profile is not found";
            throw new NotFoundException(this.msg);
        }
        // Return null if user does not possess any roles.
        if (userDto.getRoles() == null || userDto.getRoles().size() <= 0) {
            return null;
        }

        // Get application roles from the AppRole DN based on criterai, if any.
        // cn=acctadmin,ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net
        List<CategoryDto> appRoleList = this.fetchAppRole(criteria);
        // Return null if criteria did not yield any matches.
        if (appRoleList == null) {
            return null;
        }

        List<CategoryDto> revokedList = new ArrayList<CategoryDto>();
        for (CategoryDto appRole : appRoleList) {
            boolean found = false;
            for (String userRole : userDto.getRoles()) {
                if (userRole.equalsIgnoreCase(appRole.getAppRoleCode())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                revokedList.add(appRole);
            }
        }
        return revokedList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#maintainRole(org.dto.CategoryDto)
     */
    @Override
    public int maintainRole(CategoryDto role) throws SecurityDaoException {
        this.validateRole(role);
        // Determine if this is a new or existing role
        boolean newRole = (this.fetchRole(role.getRoleName()) == null);
        if (newRole) {
            this.insertRole(role);
        }
        else {
            this.updateRole(role);
        }
        return 1;
    }

    /**
     * Inserts a role at DN,
     * <i>cn=xxxxx,ou=Roles,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param role
     *            The role as a DTO
     * @throws DatabaseException
     */
    private void insertRole(CategoryDto role) throws DatabaseException {
        LdapAddOperation op = new LdapAddOperation();
        // Handle required fields
        String dn = "cn=" + role.getRoleName() + ",ou=Roles,ou=LookupCodes";
        op.setDn(dn);
        op.addListAttribute("objectClass", "RMT2Roles");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("cn", role.getRoleName());
        op.addAttribute("description", role.getRoleDescription());

        // Add role to LDAP server
        this.ldap.insertRow(op, false);
        return;
    }

    /**
     * Updates a role at DN,
     * <i>cn=xxxxx,ou=Roles,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param role
     *            The role as a DTO
     * @throws DatabaseException
     */
    private void updateRole(CategoryDto role) throws DatabaseException {
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle required fields
        String dn = "cn=" + role.getRoleName() + ",ou=Roles,ou=LookupCodes";
        op.setDn(dn);

        if (role.getRoleDescription() != null) {
            op.addAttribute("description", role.getRoleDescription(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        // Add role to LDAP server
        this.ldap.updateRow(op);
        return;
    }

    /**
     * This method is responsble for validating a role profile. The name and
     * description of the application are to have values.
     * 
     * @param app
     *            {@link com.bean.Application Application}
     * @throws RoleDaoException
     */
    private void validateRole(CategoryDto role) throws RoleDaoException {
        if (role.getRoleName() == null || role.getRoleName().length() <= 0) {
            this.msg = "Role name cannot be blank";
            throw new RoleDaoException(this.msg);
        }

        if (role.getRoleDescription() == null
                || role.getRoleDescription().length() <= 0) {
            this.msg = "Role description cannot be blank";
            throw new RoleDaoException(this.msg);
        }
    }

    /**
     * Deleted a role at DN,
     * <i>cn=xxxxx,ou=Roles,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param roleName
     *            The name of the role to delete.
     * @throws SecurityDaoException
     */
    @Override
    public int deleteRole(String roleName) throws SecurityDaoException {
        if (roleName == null) {
            this.msg = "Role name is required for delete operation";
            throw new RoleDaoException(this.msg);
        }
        String dn = "cn=" + roleName + ",ou=Roles,ou=LookupCodes";
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#maintainAppRole(org.dto.CategoryDto)
     */
    @Override
    public int maintainAppRole(CategoryDto appRole) throws SecurityDaoException {
        this.validateAppRole(appRole);
        // Determine if this is a new or existing application role
        CategoryDto criteria = Rmt2OrmDtoFactory
                .getNewAppRoleCategoryInstance();
        criteria.setAppRoleCode(appRole.getAppRoleCode());
        boolean newAppRole = (this.fetchAppRole(criteria) == null);
        if (newAppRole) {
            this.insertAppRole(appRole);
        }
        else {
            this.updateAppRole(appRole);
        }
        return 1;
    }

    /**
     * Inserts an application role at DN,
     * <i>cn=xxxxxx,ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param appRole
     *            The application role as a DTO
     * @throws DatabaseException
     */
    private void insertAppRole(CategoryDto appRole) throws DatabaseException {
        LdapAddOperation op = new LdapAddOperation();
        // Handle required fields
        String dn = "cn=" + appRole.getAppRoleCode()
                + ",ou=AppRole,ou=LookupCodes";
        op.setDn(dn);
        op.addListAttribute("objectClass", "RMT2AppRoles");
        op.addListAttribute("objectClass", "top");
        op.addAttribute("cn", appRole.getAppRoleCode());
        op.addAttribute("description", appRole.getAppRoleDescription());
        op.addAttribute("appId", appRole.getAppName());
        op.addAttribute("appRoleName", appRole.getAppRoleName());
        op.addAttribute("roleId", appRole.getRoleName());

        // Add application role to LDAP server
        this.ldap.insertRow(op, false);
        return;
    }

    /**
     * Updates an application role at DN,
     * <i>cn=xxxxxx,ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param role
     *            The role as a DTO
     * @throws DatabaseException
     */
    private void updateAppRole(CategoryDto appRole) throws DatabaseException {
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle required fields
        String dn = "cn=" + appRole.getAppRoleCode()
                + ",ou=AppRole,ou=LookupCodes";
        op.setDn(dn);
        if (appRole.getAppRoleName() != null) {
            op.addAttribute("appRoleName", appRole.getAppRoleName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (appRole.getAppRoleDescription() != null) {
            op.addAttribute("description", appRole.getAppRoleDescription(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (appRole.getAppName() != null) {
            op.addAttribute("appId", appRole.getAppName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (appRole.getAppRoleName() != null) {
            op.addAttribute("appRoleName", appRole.getAppRoleName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }
        if (appRole.getRoleName() != null) {
            op.addAttribute("roleId", appRole.getRoleName(),
                    LdapClient.MOD_OPERATION_REPLACE);
        }

        // update application role to LDAP server
        this.ldap.updateRow(op);
        return;
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
    private void validateAppRole(CategoryDto appRole) throws RoleDaoException {
        if (appRole == null) {
            this.msg = "Application role object is invalid";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getAppRoleCode() == null
                || appRole.getAppRoleCode().length() <= 0) {
            this.msg = "Application role code is required";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getAppRoleName() == null
                || appRole.getAppRoleName().length() <= 0) {
            this.msg = "Application role name is required";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getAppRoleDescription() == null
                || appRole.getAppRoleDescription().length() <= 0) {
            this.msg = "Application role description cannot be blank";
            throw new RoleDaoException(this.msg);
        }
        if (appRole.getRoleName() == null) {
            this.msg = "Role name is required";
            throw new RoleDaoException(this.msg);
        }
    }

    /**
     * Deletes an application role at DN,
     * <i>cn=xxxxxx,ou=AppRole,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * 
     * @param appRoleCode
     *            The application role code
     * @return The number of targets effected by the transaction.
     * @throws SecurityDaoException
     */
    @Override
    public int deleteAppRole(String appRoleCode) throws SecurityDaoException {
        if (appRoleCode == null) {
            this.msg = "Application role name is required for delete operation";
            throw new RoleDaoException(this.msg);
        }
        String dn = "cn=" + appRoleCode + ",ou=AppRole,ou=LookupCodes";
        LdapDeleteOperation op = new LdapDeleteOperation();
        op.setDn(dn);
        return this.ldap.deleteRow(op);
    }

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

    public int maintainUserAppRole(CategoryDto userAppRole,
            String[] assignedRoles, String[] revokedRoles)
            throws SecurityDaoException {
        if (userAppRole == null) {
            this.msg = "The user application role object is invalid";
            throw new SecurityDaoException(this.msg);
        }
        if (userAppRole.getUsername() == null) {
            this.msg = "User login id is requried";
            throw new SecurityDaoException(this.msg);
        }
        if (userAppRole.getAppName() == null) {
            this.msg = "Application name is requried";
            throw new SecurityDaoException(this.msg);
        }
        if (assignedRoles == null) {
            this.msg = "The list of roles to assign is invalid";
            throw new SecurityDaoException(this.msg);
        }
        if (assignedRoles.length < 1) {
            this.msg = "The list of roles to assign is required, but was found to be empty";
            throw new SecurityDaoException(this.msg);
        }

        // Verify user and get current list of roles
        UserDto user = this.getUserByUsername(userAppRole.getUsername());
        if (user == null) {
            this.msg = "Unable to maintain user application role(s) due to user, "
                    + userAppRole.getUsername() + ", does not exist";
            throw new SecurityDaoException(this.msg);
        }

        // Get master list of roles targeting a particular application
        LdapAppRoles dummy = null;
        CategoryDto appRoleCriteria = LdapDtoFactory
                .getApplicationRoleDtoInstance(dummy);
        appRoleCriteria.setAppName(userAppRole.getAppName());
        List<CategoryDto> appRoleList = null;
        try {
            appRoleList = this.fetchAppRole(appRoleCriteria);
        } catch (Exception e) {
            this.msg = "Unable to maintain user application role(s) due to LDAP data access error";
            throw new SecurityDaoException(this.msg, e);
        }
        if (appRoleList == null) {
            this.msg = "Unable to maintain user application role(s) due to application has not been cofigured for any roles ["
                    + userAppRole.getAppName() + "]";
            throw new SecurityDaoException(this.msg);
        }
        StringBuffer appRoleCodeBuf = new StringBuffer();
        for (CategoryDto appRole : appRoleList) {
            appRoleCodeBuf.append(appRole.getAppRoleCode());
        }

        // Prepare the LDAP modify operation object
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle the DN
        String dn = "loginid=" + userAppRole.getUsername() + ",ou=People";
        op.setDn(dn);
        // Make sure that all of the user's current roles that are unrelated
        // to the target application are captured in the transaction.
        for (String userRole : user.getRoles()) {
            if (appRoleCodeBuf.indexOf(userRole) == -1) {
                op.addListAttribute("ar", userRole,
                        LdapClient.MOD_OPERATION_REPLACE);
            }
        }
        // Add the new application roles
        if (assignedRoles != null) {
            for (String role : assignedRoles) {
                op.addListAttribute("ar", role,
                        LdapClient.MOD_OPERATION_REPLACE);
            }
        }

        // Apply updates to the LDAP server
        return this.ldap.updateRow(op);
    }

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
    @Override
    public int deleteUserAppRoles(UserDto user, String[] appRoles)
            throws SecurityDaoException {
        if (user == null) {
            this.msg = "User login id is requried";
            throw new SecurityDaoException(this.msg);
        }
        if (appRoles == null) {
            this.msg = "The list of roles to delete is invalid";
            throw new SecurityDaoException(this.msg);
        }
        if (appRoles.length < 1) {
            this.msg = "The list of roles to delete is required, but was found to be empty";
            throw new SecurityDaoException(this.msg);
        }

        // Build a String from the list of role code names targeted for deletion
        StringBuffer roleDeleteBuf = new StringBuffer();
        for (String role : appRoles) {
            roleDeleteBuf.append(role);
        }

        // Prepare the LDAP modify operation object
        LdapModifyOperation op = new LdapModifyOperation();
        // Handle the DN
        String dn = "loginid=" + user + ",ou=People";
        op.setDn(dn);
        // Begin to remove those roles targeted for deletion from the user's
        // list of roles.
        for (String userRole : user.getRoles()) {
            if (roleDeleteBuf.indexOf(userRole) == -1) {
                op.addListAttribute("ar", userRole,
                        LdapClient.MOD_OPERATION_REPLACE);
            }
        }

        // Apply updates to the LDAP server
        return this.ldap.updateRow(op);
    }

    private UserDto getUserByUsername(String userName) {
        List<UserDto> userList = null;
        UserDaoFactory uf = new UserDaoFactory();
        UserDao uDao = uf.createLdapDao();
        try {
            UserDto userCriteria = Rmt2OrmDtoFactory.getNewUserInstance();
            userCriteria.setUsername(userName);
            userList = uDao.fetchUserProfile(userCriteria);
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            }
            return null;
        } catch (Exception e) {
            this.msg = "Error obtaining user profile from the LDAP server";
            throw new RoleDaoException(this.msg);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#deleteRole(int)
     */
    @Override
    public int deleteRole(int roleId) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#deleteAppRole(int)
     */
    @Override
    public int deleteAppRole(int uid) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.roles.RoleDao#deleteUserAppRoles(java.lang.String,
     * java.lang.String[])
     */

    /**
     * Not supported
     */
    @Override
    public CategoryDto fetchRole(int roleId) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public CategoryDto fetchAppRole(int uid) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public String[] fetchAppRoleIdList(String[] roles) throws SecurityDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int maintainUserAppRole(UserDto user, String[] assignedRoles, String[] revokedRoles)
            throws SecurityDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<CategoryDto> fetchRole(CategoryDto criteria)
            throws SecurityDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CategoryDto> fetchUserAppRole(CategoryDto criteria)
            throws SecurityDaoException {
        // TODO Auto-generated method stub
        return null;
    }

}
