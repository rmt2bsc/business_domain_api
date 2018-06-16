package org.modules.roles;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoException;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An api implemetation of {@link RoleApi} that provides functionality for
 * managing Role data.
 * 
 * @author rterrell
 * 
 */
class RoleApiImpl extends AbstractTransactionApiImpl implements RoleApi {

    private static final Logger logger = Logger.getLogger(RoleApiImpl.class);

    private RoleDao dao;

    /**
     * Create an RoleApiImpl object that initializes the DAO factory.
     */
    RoleApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = RoleDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("RoleApi is initialized by default constructor");
    }

    /**
     * Create a RoleApiImpl using the specified application name.
     * 
     * @param appName
     *            the application name
     */
    RoleApiImpl(String appName) {
        super(appName);
        this.dao = RoleDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("RoleApi is initialized by application name, " + appName);
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
            throw new InvalidDataException("Role Category criteria object is required", e);
        }

        try {
            List<CategoryDto> list = dao.fetchRole(criteria);
            this.msg = "Total roles retrieved using custom criteria: " + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch Roles using custom criteria";
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }
    
    /**
     * Persist changes to a single Role entity to the database.
     * <p>
     * This method will handle SQL inserts and updates where applicable. When
     * the entity's unique id is equal to zero, an insert is performed. When the
     * unique id is greater than zero, an update is performed.
     * 
     * @param dto
     *            An instance of {@link CategoryDto} representing the Role data
     *            to be applied to the database.
     * @return the total number of rows effecting existing records, or the
     *         unique identifier for a new record.
     * @throws SecurityModuleException
     * @throws {@link NotFoundException} 
     *         <i>role</i> is not found in the database
     */
    @Override
    public int update(CategoryDto role) throws SecurityModuleException {
        this.validateRole(role);

        if (role.getRoleId() > 0) {
            try {
                CategoryDto origRec = dao.fetchRole(role.getRoleId());
                Verifier.verifyNotNull(origRec);
                role.setDateCreated(origRec.getDateCreated());
            }
            catch (VerifyException e) {
                this.msg = "Role does not exists [role id=" + role.getRoleId() + "]";
                throw new NotFoundException(this.msg, e);
            }
            catch (RoleDaoException e) {
                this.msg = "Unable to verify the existence of Role";
                throw new RoleApiException(this.msg, e);
            }
        }
        
        try {
            dao.setDaoUser(this.apiUser);
            int rc = dao.maintainRole(role);
            this.msg = "Role, " + role.getRoleName() + ", was updated successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update Role identitfied as " + role.getRoleName();
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
    public int delete(int roleId) throws SecurityModuleException {
        try {
            int rc = dao.deleteRole(roleId);
            this.msg = "Role, " + roleId + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (SecurityDaoException e) {
            this.msg = "Unable to delete Role identitfied by id, " + roleId;
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

     /**
     * This method is responsble for validating a role profile. The name and
     * description of the application are to have values.
     * 
     * @param role
     *            {@link CategoryDto}
     * @throws InvalidDataException
     */
    protected void validateRole(CategoryDto role) throws InvalidDataException {
        try {
            Verifier.verifyNotNull(role);
        } catch (VerifyException e) {
            throw new InvalidDataException("Role object is required", e);
        }

        try {
            Verifier.verifyNotEmpty(role.getRoleName());
        } catch (VerifyException e) {
            this.msg = "Role Name is required";
            throw new InvalidDataException(this.msg, e);
        }

        try {
            Verifier.verifyNotEmpty(role.getRoleDescription());
        } catch (VerifyException e) {
            this.msg = "Role description is required";
            throw new InvalidDataException(this.msg, e);
        }
    }
}
