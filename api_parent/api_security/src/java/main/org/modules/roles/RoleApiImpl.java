package org.modules.roles;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.modules.SecurityModuleException;

import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An api implemetation of {@link RoleApi} that provides functionality for
 * managing Role data.
 * 
 * @author rterrell
 * 
 */
class RoleApiImpl extends AbstractTransactionApiImpl implements RoleApi {

    private static final Logger logger = Logger.getLogger(RoleApiImpl.class);

    private RoleDaoFactory daoFactory;

    /**
     * Create a RoleApiImpl that initializs the DAO factory.
     */
    protected RoleApiImpl() {
        this.daoFactory = new RoleDaoFactory();
    }

    /**
     * Not supported
     */
    @Override
    public CategoryDto get(int roleId) throws SecurityModuleException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.RoleApi#get(java.lang.String)
     */
    @Override
    public CategoryDto get(String roleName) throws SecurityModuleException {
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            CategoryDto dto = dao.fetchRole(roleName);
            if (dto == null) {
                this.msg = "Role, " + roleName + ", was not found";
            }
            else {
                this.msg = "Role, " + roleName + ", was retrieved successfully";
            }
            logger.info(this.msg);
            return dto;
        } catch (Exception e) {
            this.msg = "Unable to fetch Role by id, " + roleName;
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
     * @see org.modules.CategoryApiModule#fetch()
     */
    @Override
    public List<CategoryDto> get() throws SecurityModuleException {
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            List<CategoryDto> list = dao.fetchRole();
            this.msg = "Total roles retrieved: "
                    + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of roles";
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
     * @see org.modules.CategoryApiModule#create()
     */
    @Override
    public CategoryDto create() {
        return Rmt2OrmDtoFactory.getNewRoleCategoryInstance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#update(org.dto.CategoryDto)
     */
    @Override
    public int update(CategoryDto role) throws SecurityModuleException {
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            dao.setDaoUser(this.apiUser);
            int rc = dao.maintainRole(role);
            this.msg = "Role, " + role.getRoleName()
                    + ", was updated successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update Role identitfied as "
                    + role.getRoleName();
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
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
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

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.RoleApi#delete(java.lang.String)
     */
    @Override
    public int delete(String roleName) throws SecurityModuleException {
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            int rc = dao.deleteRole(roleName);
            this.msg = "Role, " + roleName + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to delete Role identitfied by id, " + roleName;
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

}
