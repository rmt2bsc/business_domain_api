package org.modules.roles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.users.UserApi;
import org.modules.users.UserApiException;
import org.modules.users.UserApiFactory;

import com.InvalidDataException;
import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An api implemetation of {@link UserAppRoleApi} that provides functionality
 * for managing User Application Role data.
 * 
 * @author rterrell
 * 
 */
class UserAppRoleApiImpl extends AbstractTransactionApiImpl implements UserAppRoleApi {

    private static final Logger logger = Logger.getLogger(UserAppRoleApiImpl.class);

    private RoleDao dao;

    /**
     * Create an AppRoleApiImpl object that initializes the DAO factory.
     */
    UserAppRoleApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = RoleDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("UserAppRoleApi is initialized by default constructor");
    }

    /**
     * Create a AppRoleApiImpl using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    UserAppRoleApiImpl(String appName) {
        super(appName);
        this.dao = RoleDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("UserAppRoleApi is initialized by application name, " + appName);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public List<CategoryDto> get(CategoryDto criteria) throws SecurityModuleException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Category criteria object is required", e);
        }

        try {
            List<CategoryDto> list = dao.fetchUserAppRole(criteria);
            this.msg = "Total user application roles retrieved using custom criteria: "
                    + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch User Application Roles using custom criteria";
            throw new AppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }
  
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.roles.UserAppRoleApi#fetchAssignedRoles(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> getAssignedRoles(CategoryDto criteria) throws UserAppRoleApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Assigned Roles Query criteria object is required", e);
        }

        try {
            Verifier.verifyNotEmpty(criteria.getUsername());
        } catch (VerifyException e) {
            this.msg = "The user's login id is required";
            throw new InvalidDataException(this.msg, e);
        }

        List<CategoryDto> list = null;
        try {
            list = dao.fetchUserAssignedRoles(criteria);
            this.msg = "Total assigned application roles found for user, " + criteria.getUsername() + ": "
                    + list.size();
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch assinged Application Roles for user, " + criteria.getUsername();
            logger.error(this.msg);
            throw new UserAppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.roles.UserAppRoleApi#fetchrevokedRoles(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> getRevokedRoles(CategoryDto criteria) throws UserAppRoleApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Revoked Roles Query criteria object is required", e);
        }

        try {
            Verifier.verifyPositive(criteria.getLoginUid());
        } catch (VerifyException e) {
            this.msg = "The user's internal unique id is required";
            throw new InvalidDataException(this.msg, e);
        }

        List<CategoryDto> list = null;
        try {
            list = dao.fetchUserRevokedRoles(criteria);
            this.msg = "Total revoked application roles found for user loginId, " + criteria.getLoginUid() + ": "
                    + list.size();
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch revoked application roles for user loginId, " + criteria.getLoginUid();
            logger.error(this.msg);
            throw new UserAppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.UserAppRoleApi#update(org.dto.CategoryDto,
     * java.util.List)
     */
    @Override
    public int update(CategoryDto userAppDetails, List<String> roles) throws UserAppRoleApiException {
        try {
            Verifier.verifyNotNull(userAppDetails);
        } catch (VerifyException e) {
            throw new InvalidDataException("UserAppRole object is required");
        }

        // Verify that we have a valid list of assigned roles. An invalid list
        // means that all user roles were revoked and there are no new roles to
        // assign.
        try {
            Verifier.verifyNotEmpty(roles);
        } catch (VerifyException e) {
            logger.warn("All roles for user, " + userAppDetails.getUsername() + ", appear to be revoked and there are no new roles to assign");
            return 0;
        }

        // Get UserLogin object
        UserDto userDto = this.getUserDto(userAppDetails.getUsername());

        // Delete all roles assoicated with a particular application for the user.
        dao.deleteUserAppRoles(userDto, null);

        // Use the list of application role code names to build an array of
        // equivalent role id's
        ArrayList<String> roleList = new ArrayList<String>(roles);
        String rolesToAssign[] = roleList.toArray(new String[roles.size()]);
        String roleIdsToAssign[] = dao.fetchAppRoleIdList(rolesToAssign);

        int rc = 0;
        try {
            rc = dao.maintainUserAppRole(userDto, roleIdsToAssign, null);
            this.msg = "Total number of roles created user, "
                    + userAppDetails.getUsername() + ", for application, "
                    + userAppDetails.getApplicationId() + ": " + rc;
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update user, " + userAppDetails.getUsername()
                    + ", with new set of roles for application, "
                    + userAppDetails.getApplicationId();
            throw new UserAppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Delete a specified list or all application roles belonging to a user.
     * 
     * @param userName
     *            The login id of the user to associate roles
     * @param appRoles
     *            A List of Strings representing the roles to removed. Set to
     *            null when all application roles are desired to be removed.
     * @return The total number of roles deleted.
     * @throws SecurityModuleException
     */
    @Override
    public int delete(String userName, List<String> appRoles) throws UserAppRoleApiException {
        boolean deleteSelectedRoles = true;
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new InvalidDataException("User name is required");
        }

        try {
            Verifier.verifyNotEmpty(appRoles);
        } catch (VerifyException e) {
            logger.warn("All application roles for user, " + userName + ", will be deleted!");
            deleteSelectedRoles = false;
        }

        // Get UserLogin object
        UserDto userDto = this.getUserDto(userName);

        // Use the list of application role code names to build an array of
        // equivalent role id's, if available
        String appRoleIdArray[] = null;
        if (deleteSelectedRoles) {
            ArrayList<String> roleList = new ArrayList<String>(appRoles);
            String appRoleArray[] = roleList.toArray(new String[appRoles.size()]);
            appRoleIdArray = dao.fetchAppRoleIdList(appRoleArray);    
        }

        // Delete user app roles
        int rc = 0;
        try {
            rc = dao.deleteUserAppRoles(userDto, appRoleIdArray);
            this.msg = "Total number of roles removed from user, " + userName + ": " + rc;
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to remove application roles for user, " + userName + ": " + appRoleIdArray;
            logger.error(this.msg);
            throw new UserAppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    private UserDto getUserDto(String userName) throws UserAppRoleApiException {
        UserApi userApi = UserApiFactory.createApiInstance();
        UserDto userCriteria = Rmt2OrmDtoFactory.getNewUserInstance();
        userCriteria.setUsername(userName);
        UserDto userDto = null;
        try {
            List<UserDto> userDtoList = userApi.getUser(userCriteria);
            if (userDtoList != null && userDtoList.size() == 1) {
                userDto = userDtoList.get(0);
            }
            return userDto;
        } catch (UserApiException e1) {
            throw new UserAppRoleApiException("Unable to otbatin UserLogin object based on userName, " + userName);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#update(org.dto.CategoryDto)
     */
    @Override
    public int update(CategoryDto dto) throws UserAppRoleApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#delete(int)
     */
    @Override
    public int delete(int uid) throws UserAppRoleApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
