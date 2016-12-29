package org.modules.roles;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.ldap.LdapAppRoles;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.ldap.LdapDtoFactory;
import org.modules.SecurityModuleException;

import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An api implemetation of {@link AppRoleApi} that provides functionality for
 * managing Application Role data.
 * 
 * @author rterrell
 * 
 */
class AppRoleApiImpl extends AbstractTransactionApiImpl implements AppRoleApi {

    private static final Logger logger = Logger.getLogger(AppRoleApiImpl.class);

    private RoleDaoFactory daoFactory;

    /**
     * Create an AppRoleApiImpl object that initializes the DAO factory.
     */
    protected AppRoleApiImpl() {
        this.daoFactory = new RoleDaoFactory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#fetch(int)
     */
    @Override
    public CategoryDto get(int appRoleId) throws SecurityModuleException {
        RoleDao dao = this.daoFactory.createLdapDao();
        try {
            CategoryDto dto = dao.fetchAppRole(appRoleId);
            this.msg = "Application Role, " + appRoleId
                    + ", was retrieved successfully";
            logger.info(this.msg);
            return dto;
        } catch (Exception e) {
            this.msg = "Unable to fetch Application Role by id, " + appRoleId;
            logger.error(this.msg);
            throw new AppRoleApiException(this.msg, e);
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
        RoleDao dao = this.daoFactory.createLdapDao();
        try {
            List<CategoryDto> list = dao.fetchAppRole();
            this.msg = "Total application roles retrieved: "
                    + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of Application Roles";
            logger.error(this.msg);
            throw new AppRoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    // /* (non-Javadoc)
    // * @see org.modules.roles.AppRoleApi#fetch(java.lang.String)
    // */
    // @Override
    // public List<CategoryDto> get(String userName) throws
    // SecurityModuleException {
    // RoleDao dao = this.daoFactory.createRmt2OrmDao();
    // try {
    // List<CategoryDto> list = dao.fetchAppRole(userName);
    // this.msg = "Total application roles retrieved by user name, " + userName
    // + ": " + (list == null ? 0 : list.size());
    // logger.info(this.msg);
    // return list;
    // }
    // catch (Exception e) {
    // this.msg = "Unable to fetch Application Roles by user, " + userName;
    // logger.error(this.msg);
    // throw new AppRoleApiException(this.msg, e);
    // }
    // finally {
    // dao.close();
    // dao = null;
    // }
    // }
    //
    // /* (non-Javadoc)
    // * @see org.modules.roles.AppRoleApi#fetch(java.lang.String,
    // java.lang.String)
    // */
    // @Override
    // public List<CategoryDto> get(String userName, String appName) throws
    // SecurityModuleException {
    // RoleDao dao = this.daoFactory.createRmt2OrmDao();
    // try {
    // List<CategoryDto> list = dao.fetchAppRole(userName, appName);
    // this.msg = "Total application roles retrieved by user name, " + userName
    // + " and application, " + appName + ": " + (list == null ? 0 :
    // list.size());
    // logger.info(this.msg);
    // return list;
    // }
    // catch (Exception e) {
    // this.msg = "Unable to fetch Application Roles by user and application, "
    // + userName + " and " + appName + ", respectively";
    // logger.error(this.msg);
    // throw new AppRoleApiException(this.msg, e);
    // }
    // finally {
    // dao.close();
    // dao = null;
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.AppRoleApi#fetch(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> get(CategoryDto criteria)
            throws SecurityModuleException {
        RoleDao dao = this.daoFactory.createLdapDao();
        try {
            List<CategoryDto> list = dao.fetchAppRole(criteria);
            this.msg = "Total application roles retrieved using custom criteria: "
                    + (list == null ? 0 : list.size());
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch Application Roles using custom criteria";
            logger.error(this.msg);
            throw new AppRoleApiException(this.msg, e);
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
        // return Rmt2OrmDtoFactory.getNewAppRoleCategoryInstance();
        LdapAppRoles dummy = null;
        return LdapDtoFactory.getApplicationRoleDtoInstance(dummy);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#update(org.dto.CategoryDto)
     */
    @Override
    public int update(CategoryDto appRole) throws SecurityModuleException {
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            dao.setDaoUser(this.apiUser);
            int rc = dao.maintainAppRole(appRole);
            this.msg = "Application Role, " + appRole.getAppRoleName()
                    + ", was updated successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update Application Role identitfied as "
                    + appRole.getAppRoleName();
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
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            int rc = dao.deleteAppRole(appRoleId);
            this.msg = "Application Role, " + appRoleId
                    + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to delete Application Role identitfied by id, "
                    + appRoleId;
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
        RoleDao dao = null;
        try {
            dao = this.daoFactory.createLdapDao();
            int rc = dao.deleteAppRole(appRoleCode);
            this.msg = "Application Role, " + appRoleCode
                    + ", was deleted successfully";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to delete Application Role identitfied by id, "
                    + appRoleCode;
            logger.error(this.msg);
            throw new RoleApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

}
