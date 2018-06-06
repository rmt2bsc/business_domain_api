package org.modules.users;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;

import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An api implemetation of {@link UserApi} that provides functionality for
 * managing User and Group data.
 * 
 * @author rterrell
 * 
 */
class UserApiImpl extends AbstractTransactionApiImpl implements UserApi {

    private static final Logger logger = Logger.getLogger(UserApiImpl.class);

    private UserDaoFactory factory;

    /**
     * Create an UserApiImpl object that initializes the DAO factory.
     */
    protected UserApiImpl() {
        super();
        this.factory = new UserDaoFactory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#fetchUser(org.dto.UserDto)
     */
    @Override
    public List<UserDto> getUser(UserDto user) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        try {
            List<UserDto> list = dao.fetchUser(user);
            this.msg = "User was retrieved successfully using custom criteria";
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch user by login id, " + user;
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#updateUser(org.dto.UserDto)
     */
    @Override
    public int updateUser(UserDto user) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        dao.setDaoUser(this.apiUser);
        try {
            int rc = dao.maintainUser(user);
            this.msg = "Changes to user was saved successfully for user, "
                    + user.getUsername();
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to save changes to user, " + user.getUsername();
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#deleteUser(int)
     */
    @Override
    public int deleteUser(int uid) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        try {
            int rc = dao.deleteUser(uid);
            this.msg = "User was deleted successfully: " + uid;
            logger.info(this.msg);
            return rc;
        } catch (UserDaoException e) {
            this.msg = "Unable to delete user, " + uid;
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#fetchGroup()
     */
    @Override
    public List<UserDto> getGroup(UserDto group) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        try {
            List<UserDto> list = dao.fetchGroup(group);
            this.msg = "master list of groups were retrieved successfully";
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of groups";
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#updateGroup(org.dto.UserDto)
     */
    @Override
    public int updateGroup(UserDto grp) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        dao.setDaoUser(this.apiUser);
        try {
            int rc = dao.maintainGroup(grp);
            this.msg = "Changes to group were saved successfully for group, "
                    + grp.getGrp();
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to save changes to group, " + grp.getGrp();
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#deleteGroup(int)
     */
    @Override
    public int deleteGroup(int grpId) throws UserApiException {
        UserDao dao = this.factory.createLdapDao();
        try {
            int rc = dao.deleteGroup(grpId);
            this.msg = "Group was deleted successfully: " + grpId;
            logger.info(this.msg);
            return rc;
        } catch (UserDaoException e) {
            this.msg = "Unable to delete group, " + grpId;
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }
}
