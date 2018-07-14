package org.modules.roles;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoException;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.modules.SecurityModuleException;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

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
     * Creates a AppRoleApiImpl object in which the configuration is identified
     * by the name of a given application.
     * 
     * @param appName
     *            the application name
     */
    AppRoleApiImpl(String appName) {
        super(appName);
        this.dao = RoleDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.setApiUser(this.apiUser);
        logger.info("AppApi is initialized by application name, " + appName);
    }

    /**
     * Create a AppRoleApiImpl using DaoClient instance that is intended
     * to be shared by another related application.
     * 
     * @param dao
     *            instance of {@link DaoClient}
     */
    AppRoleApiImpl(DaoClient dao) {
        super(dao);
        this.dao = RoleDaoFactory.createRmt2OrmDao(this.getSharedDao());
        logger.info("AppApi is initialized using a shared DAO client");
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
            throw new InvalidDataException("AppRole Category criteria object is required", e);
        }

        try {
            List<CategoryDto> list = dao.fetchAppRole(criteria);
            this.msg = "Total application roles retrieved using custom criteria: " + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch Application Roles using custom criteria";
            throw new AppRoleApiException(this.msg, e);
        }
    }

    /**
     * Persist changes to a single AppRole entity to the database.
     * <p>
     * This method will handle SQL inserts and updates where applicable. When
     * the entity's unique id is equal to zero, an insert is performed. When the
     * unique id is greater than zero, an update is performed.
     * 
     * @param dto
     *            An instance of {@link CategoryDto} representing the AppRole data
     *            to be applied to the database.
     * @return the total number of rows effecting existing records, or the
     *         unique identifier for a new record.
     * @throws SecurityModuleException
     * @throws {@link NotFoundException} 
     *         <i>appRole</i> is not found in the database
     */
    @Override
    public int update(CategoryDto appRole) throws SecurityModuleException {
        this.validateAppRole(appRole);

        if (appRole.getAppRoleId() > 0) {
            try {
                CategoryDto origRec = dao.fetchAppRole(appRole.getAppRoleId());
                Verifier.verifyNotNull(origRec);
                appRole.setDateCreated(origRec.getDateCreated());
            }
            catch (VerifyException e) {
                this.msg = "AppRole does not exists [app role id=" + appRole.getAppRoleId() + "]";
                throw new NotFoundException(this.msg, e);
            }
            catch (RoleDaoException e) {
                this.msg = "Unable to verify the existence of AppRole";
                throw new AppRoleApiException(this.msg, e);
            }
        }
        
        try {
            dao.setDaoUser(this.apiUser);
            int rc = dao.maintainAppRole(appRole);
            this.msg = "Application Role, " + appRole.getAppRoleName() + ", was updated successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update Application Role identitfied as " + appRole.getAppRoleName();
            throw new AppRoleApiException(this.msg, e);
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
            throw new AppRoleApiException(this.msg, e);
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
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(appRole.getAppRoleName());
        } catch (VerifyException e) {
            this.msg = "Application Role Name is required";
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(appRole.getAppRoleDescription());
        } catch (VerifyException e) {
            this.msg = "Application Role Description is required";
            throw new InvalidDataException(this.msg, e);
        }
    }
}
