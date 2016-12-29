package org.modules.roles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dto.CategoryDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An api implemetation of {@link UserAppRoleApi} that provides functionality
 * for managing User Application Role data.
 * 
 * @author rterrell
 * 
 */
class UserAppRoleApiImpl extends AbstractTransactionApiImpl implements
        UserAppRoleApi {

    private static final Logger logger = Logger
            .getLogger(UserAppRoleApiImpl.class);

    private RoleDaoFactory daoFactory;

    /**
     * Create an UserAppRoleApiImpl object that initializes the DAO factory.
     */
    protected UserAppRoleApiImpl() {
        this.daoFactory = new RoleDaoFactory();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#create()
     */
    @Override
    public CategoryDto create() {
        return Rmt2OrmDtoFactory.getNewExtUserAppRoleCategoryInstance(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.roles.UserAppRoleApi#fetch(java.lang.String)
     */
    @Override
    public List<CategoryDto> get(String userName)
            throws UserAppRoleApiException {
        RoleDao dao = this.daoFactory.createLdapDao();
        List<CategoryDto> list;
        try {
            list = dao.fetchUserAppRole(userName);
            return list;
        } catch (SecurityDaoException e) {
            throw new UserAppRoleApiException(e);
        }
    }

    // public List<CategoryDto> get(String userName) throws
    // UserAppRoleApiException {
    // RoleSecurityApiFactory f = new RoleSecurityApiFactory();
    // AppRoleApi api = f.createAppRoleApi();
    // List<CategoryDto> list;
    // try {
    // list = api.get(userName);
    // return list;
    // }
    // catch (SecurityModuleException e) {
    // e.printStackTrace();
    // throw new RuntimeException(e);
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.roles.UserAppRoleApi#fetchAssignedRoles(org.dto.CategoryDto)
     */
    @Override
    public List<CategoryDto> getAssignedRoles(CategoryDto criteria)
            throws UserAppRoleApiException {
        String loginId = criteria.getUsername() == null ? "<user unknown>"
                : criteria.getUsername();
        RoleDao dao = this.daoFactory.createLdapDao();
        List<CategoryDto> list = null;
        try {
            list = dao.fetchUserAssignedRoles(criteria);
            this.msg = "Total assigned application roles found for user, "
                    + loginId + ": " + list.size();
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch assinged Application Roles for user, "
                    + loginId;
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
    public List<CategoryDto> getRevokedRoles(CategoryDto criteria)
            throws UserAppRoleApiException {
        String loginId = criteria.getUsername() == null ? "<user unknown>"
                : criteria.getUsername();
        RoleDao dao = this.daoFactory.createLdapDao();
        List<CategoryDto> list = null;
        try {
            list = dao.fetchUserRevokedRoles(criteria);
            this.msg = "Total revoked application roles found for user, "
                    + loginId + ": " + list.size();
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch revoked application roles for user, "
                    + loginId;
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
    public int update(CategoryDto userAppDetails, List<String> roles)
            throws UserAppRoleApiException {
        ArrayList<String> roleList = new ArrayList<String>(roles);
        String list[] = roleList.toArray(new String[roles.size()]);
        int rc = 0;
        RoleDao dao = this.daoFactory.createLdapDao();
        try {
            rc = dao.maintainUserAppRole(userAppDetails, list, null);
            this.msg = "Total number of roles created user, "
                    + userAppDetails.getUsername() + ", for application, "
                    + userAppDetails.getApplicationId() + ": " + rc;
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to update user, " + userAppDetails.getUsername()
                    + ", with new set of roles for application, "
                    + userAppDetails.getApplicationId();
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
     * @see org.modules.roles.UserAppRoleApi#delete(java.lang.String,
     * java.util.List)
     */
    @Override
    public int delete(String userName, List<String> appRoles)
            throws UserAppRoleApiException {
        ArrayList<String> roleList = new ArrayList<String>(appRoles);
        String list[] = roleList.toArray(new String[appRoles.size()]);
        int rc = 0;
        RoleDao dao = this.daoFactory.createLdapDao();
        try {
            rc = dao.deleteUserAppRoles(userName, list);
            this.msg = "Total number of roles removed from user, " + userName
                    + ": " + rc;
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to remove application roles for user, "
                    + userName + ": " + list;
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
     * @see org.modules.CategoryApiModule#fetch(int)
     */
    @Override
    public CategoryDto get(int uid) throws UserAppRoleApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#fetch()
     */
    @Override
    public List<CategoryDto> get() throws UserAppRoleApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#update(org.dto.CategoryDto)
     */
    @Override
    public int update(CategoryDto dto) throws UserAppRoleApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.CategoryApiModule#delete(int)
     */
    @Override
    public int delete(int uid) throws UserAppRoleApiException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
