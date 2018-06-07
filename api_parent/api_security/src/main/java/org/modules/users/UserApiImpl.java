package org.modules.users;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.user.InvalidUserInstanceException;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;
import org.modules.SecurityConstants;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * An api implemetation of {@link UserApi} that provides functionality for
 * managing User and Group data.
 * 
 * @author rterrell
 * 
 */
class UserApiImpl extends AbstractTransactionApiImpl implements UserApi {

    private static final Logger logger = Logger.getLogger(UserApiImpl.class);

    private UserDaoFactory daoFact;
    private UserDao dao;

    /**
     * Create an UserApiImpl object that initializes the DAO factory.
     */
    UserApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = this.daoFact.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("User Api is initialized by default constructor");
    }

    /**
     * Create a UserApiImpl using the specified application name.
     * 
     * @param appName
     *            the user name
     */
    UserApiImpl(String appName) {
        super(appName);
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("User Api is initialized by application name, " + appName);
    }
    
    @Override
    public void init() {
        super.init();
        this.daoFact = new UserDaoFactory();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#fetchUser(org.dto.UserDto)
     */
    @Override
    public List<UserDto> getUser(UserDto criteria) throws UserApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("User crtieria object is required", e);
        }
        
        try {
            List<UserDto> list = dao.fetchUser(criteria);
            this.msg = "User was retrieved successfully using custom criteria";
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch user by login id, " + criteria;
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
        this.validateUser(user);
        dao.setDaoUser(this.apiUser);
        try {
            int rc = dao.maintainUser(user);
            this.msg = "Changes to user was saved successfully for user, " + user.getUsername();
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

    /**
     * Validates a UserDto object based on UserLogin.
     * 
     * @param user
     *            the user instance to validate
     * @throws InvalidUserInstanceException
     *             <ol>
     *             <li>When <i>user</i> is null</li>
     *             <li>The internal unique id is less than or equal to zero</li>
     *             <li>The first name is null</li>
     *             <li>The last name is null</li>
     *             <li>The group id is less than or equal to zero</li>
     *             <li>The password is null</li>
     *             <li></li>
     *             </ol>
     */
    protected void validateUser(UserDto user) throws InvalidUserInstanceException {
        try {
            Verifier.verifyNotNull(user);
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("User DTO object is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(user.getUsername());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("Username is required", e);
        }

        try {
            Verifier.verifyNotEmpty(user.getFirstname());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("User first name is required", e);
        }

        try {
            Verifier.verifyNotEmpty(user.getLastname());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("User last name is required", e);
        }

        try {
            Verifier.verifyPositive(user.getGroupId());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("Group id is required for user", e);
        }

        try {
            Verifier.verifyNotEmpty(user.getPassword());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("User password is required", e);
        }

        return;
    }

    /**
     * Validates a UserDto object based on UserGroup.
     * 
     * @param group
     *            the user group instance to validate
     * @throws InvalidUserInstanceException
     *             <ol>
     *             <li>When <i>group</i> is null</li>
     *             <li>The description is null or empty</li>
     *             <li></li>
     *             </ol>
     */
    protected void validateGroup(UserDto group) throws InvalidUserInstanceException {
        try {
            Verifier.verifyNotNull(group);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("User Group crtieria object is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(group.getGrpDescription());
        }
        catch (VerifyException e) {
            throw new InvalidUserInstanceException("Group Description is required", e);
        }
        return;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.users.UserApi#deleteUser(int)
     */
    @Override
    public int deleteUser(int uid) throws UserApiException {
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
    public List<UserDto> getGroup(UserDto criteria) throws UserApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("User Group crtieria object is required", e);
        }
        
        try {
            List<UserDto> list = dao.fetchGroup(criteria);
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
        this.validateGroup(grp);
        
        dao.setDaoUser(this.apiUser);
        try {
            int rc = dao.maintainGroup(grp);
            this.msg = "Changes to group were saved successfully for group, " + grp.getGrp();
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
