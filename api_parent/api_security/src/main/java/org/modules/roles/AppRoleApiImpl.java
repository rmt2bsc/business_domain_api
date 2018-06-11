package org.modules.roles;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoException;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An api implemetation of {@link AppRoleApi} that provides functionality for
 * managing Application Role data.
 * 
 * @author rterrell
 * 
 */
class AppRoleApiImpl extends AbstractTransactionApiImpl implements AppRoleApi {

    private static final Logger logger = Logger.getLogger(AppRoleApiImpl.class);

    private RoleDao dao;

    /**
     * Create an AppRoleApiImpl object that initializes the DAO factory.
     */
    AppRoleApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = RoleDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("AppRoleApi is initialized by default constructor");
    }

    /**
     * Create a AppRoleApiImpl using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    AppRoleApiImpl(String appName) {
        super(appName);
        this.dao = RoleDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("AppApi is initialized by application name, " + appName);
    }


    @Override
    public void init() {
        super.init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.AppRoleApi#fetch(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> get(CategoryDto criteria) throws SecurityModuleException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Category criteria object is required", e);
        }

        try {
            List<CategoryDto> list = dao.fetchAppRole(criteria);
            this.msg = "Total application roles retrieved using custom criteria: "
                    + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch Application Roles using custom criteria";
            throw new AppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#update(org.dto.CategoryDto)
     */
    @Override
    public int update(CategoryDto appRole) throws SecurityModuleException {
        this.validateAppRole(appRole);

        try {
            dao.setDaoUser(this.apiUser);
            int rc = dao.maintainAppRole(appRole);
            this.msg = "Application Role, " + appRole.getAppRoleName() + ", was updated successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update Application Role identitfied as " + appRole.getAppRoleName();
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#delete(int)
     */
    @Override
    public int delete(int appRoleId) throws SecurityModuleException {
        try {
            int rc = dao.deleteAppRole(appRoleId);
            this.msg = "Application Role, " + appRoleId + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to delete Application Role identitfied by id, " + appRoleId;
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Delete an application role by its application role code.
     * 
     * @param appRoleCode
     *            The application role code
     * @return the total number of records deleted.
     * @throws SecurityModuleException
     */
    @Override
    public int delete(String appRoleCode) throws SecurityModuleException {
        try {
            int rc = dao.deleteAppRole(appRoleCode);
            this.msg = "Application Role, " + appRoleCode + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to delete Application Role identitfied by id, " + appRoleCode;
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * This method is responsble for validating an application profile. The
     * name, description, code, application id, and role id must contain valid
     * values.
     * 
     * @param app
     *            an instance of {@link AppRole}
     * @throws InvalidDataException
     */
    protected void validateAppRole(CategoryDto appRole) throws InvalidDataException {
        try {
            Verifier.verifyNotNull(appRole);
        } catch (VerifyException e) {
            throw new InvalidDataException("AppRole object is required", e);
        }

        try {
            Verifier.verifyPositive(appRole.getApplicationId());
        } catch (VerifyException e) {
            this.msg = "Application Id is required";
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyPositive(appRole.getRoleId());
        } catch (VerifyException e) {
            this.msg = "Role Id is required";
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(appRole.getAppRoleCode());
        } catch (VerifyException e) {
            this.msg = "Application Role Code is required";
            throw new RoleDaoException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(appRole.getAppRoleName());
        } catch (VerifyException e) {
            this.msg = "Application Role Name is required";
            throw new RoleDaoException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(appRole.getAppRoleDescription());
        } catch (VerifyException e) {
            this.msg = "Application Role Description is required";
            throw new RoleDaoException(this.msg, e);
        }
    }
}
