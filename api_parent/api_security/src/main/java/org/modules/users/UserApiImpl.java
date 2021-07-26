package org.modules.users;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.UserDto;
import org.modules.authentication.CryptoUtils;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * An api implemetation of {@link UserApi} that provides functionality for
 * managing User and Group data.
 * 
 * @author rterrell
 * 
 */
class UserApiImpl extends AbstractTransactionApiImpl implements UserApi {

    private static final Logger logger = Logger.getLogger(UserApiImpl.class);

    private UserDao dao;


    /**
     * Creates a UserApiImpl object in which the configuration is identified by
     * the name of a given application.
     * 
     * @param appName
     *            the user name
     */
    UserApiImpl(String appName) {
        super(appName);
        this.dao = UserDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        this.setApiUser(this.apiUser);
        logger.info("User Api is initialized by application name, " + appName);
    }
    
    @Override
    public void init() {
        super.init();
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
            List<UserDto> list = dao.fetchUserProfile(criteria);
            this.msg = "User was retrieved successfully using custom criteria";
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch user by login id, " + criteria;
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
        }
    }

    /**
     * Updates a user's profile.
     * 
     * @param user
     *            the user instance to validate
     * @throws UserApiException
     *             when <i>user</i> fails validations, password encryption
     *             operation fails, or a DAO operation fails.
     * 
     */
    @Override
    public int updateUser(UserDto user) throws UserApiException {
        this.validateUser(user);
        this.verifyUserForUpdate(user);
        
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
        }
    }

    /**
     * Changes the user's password.
     * 
     * @param userName
     *            the user's username or network id
     * @param newPassword
     *            the new password
     * @throws InvalidUserInstanceException
     *             <i>username</i> and/or <i>newPassword</i> is blank or null
     * @throws UserApiException
     *             The <iuserName</i> and/or <i>newPassword</i> is null or
     *             blank, the user's profile does not exists
     */
    @Override
    public void changePassword(String userName, String newPassword) throws UserApiException {
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new InvalidUserInstanceException("Username is required for change password operation", e);
        }
        try {
            Verifier.verifyNotEmpty(newPassword);
        } catch (VerifyException e) {
            throw new InvalidUserInstanceException("New user password is required for change password operation", e);
        }

        UserDto origRec = null;
        // Ensure the user exists prior to applying modifications
        try {
            origRec = dao.fetchUserProfile(userName);
            Verifier.verifyNotNull(origRec);
        } catch (VerifyException e) {
            this.msg = "User Profile does not exists for username: " + userName;
            throw new NotFoundException(this.msg, e);
        } catch (UserDaoException e) {
            this.msg = "Unable to verify the existence of User Profile by username, " + userName;
            throw new UserApiException(this.msg, e);
        }

        // Encrypt password
        origRec.setPassword(newPassword);
        this.encryptedPassword(origRec);

        // Apply updates
        dao.setDaoUser(this.apiUser);
        try {
            int rc = dao.maintainUser(origRec);
            if (rc > 0) {
                this.msg = "Password changed successfully for user, " + userName;
            }
            else {
                this.msg = "Password change operation was successful, but the password was not changed for user, " + userName;
            }
            logger.info(this.msg);
            return;
        } catch (UserDaoException e) {
            this.msg = "A UserDao error occurred updating password for user, " + userName;
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
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

        // Validate password only when user profile is new.
        if (user.getLoginUid() == 0) {
            try {
                Verifier.verifyNotEmpty(user.getPassword());
            } catch (VerifyException e) {
                throw new InvalidUserInstanceException("User password is required", e);
            }
        }

        return;
    }

    /**
     * Verifies the state of the user object is acceptable based on the proposed
     * update operation.
     * <p>
     * The following conditions must pass:
     * <ul>
     * <li>The user's profile must exists in the system</li>
     * <li>When changing the username, the usernmae must not exists for any
     * other user profiles in the systme</li>
     * <li>The user's profile must exists in the system</li>
     * </ul>
     * 
     * @param user
     *            an instance of {@link UserDto}
     * @throws UsernameAleadyExistsException
     *             A user profile already exists in the system by
     *             <i>user.userName</i>
     * @throws NotFoundException
     *             <i>user.userName</i> is already associated with a user
     *             profile in the system.
     * @throws UserApiException
     *             A paroble occurred encrypting the user's password.
     */
    protected void verifyUserForUpdate(UserDto user) throws UsernameAleadyExistsException, NotFoundException, UserApiException {
        
        boolean pwNew = false;
        UserDto origRec = null;
        if (user.getLoginUid() > 0) {
            // Ensure the user exists prior to applying modifications
            try {
                origRec = dao.fetchUserProfile(user.getLoginUid());
                Verifier.verifyNotNull(origRec);
                // Capture original date created before persisting the actual
                // updates.
                user.setDateCreated(origRec.getDateCreated());
            } catch (VerifyException e) {
                this.msg = "User Profile does not exists [user id=" + user.getLoginUid() + "]";
                throw new NotFoundException(this.msg, e);
            } catch (UserDaoException e) {
                this.msg = "Unable to verify the existence of User Profile";
                throw new UserApiException(this.msg, e);
            }
        }
        else {
            pwNew = true;
        }

        // TODO: Confirm if modification of existing username should be allowed
        // If changing the username of an existing profile or the profile is
        // new, verify that new username does not exists.
        boolean userNameChanged =
                (user.getLoginUid() > 0 && origRec != null && !user.getUsername().equals(origRec.getUsername()));
        if (userNameChanged || user.getLoginUid() == 0) {
            try {
                Object results = dao.fetchUserProfile(user.getUsername());
                Verifier.verifyNull(results);
            } catch (VerifyException e) {
                this.msg = "User Profile cannot be created or modified due to username, "
                        + user.getUsername() + ",  already exists!";
                throw new UsernameAleadyExistsException(this.msg, e);
            } catch (UserDaoException e) {
                this.msg = "Unable to verify the uniqueness of the username";
                throw new UserApiException(this.msg, e);
            }
        }
        
        // Check for password updates. Encrypt password in cases where the
        // password is new or is being changed.
        if (pwNew) {
            this.encryptedPassword(user);
        }
    }
    


    private String encryptedPassword(UserDto user) throws UserApiException {
        String hashPassPw = null;
        try {
            byte[] pwHash = CryptoUtils.computeHash(user.getUsername() + user.getPassword());
            hashPassPw = CryptoUtils.byteArrayToHexString(pwHash);
        } catch (NoSuchAlgorithmException e) {
            throw new UserApiException("Unable to encrypt password while maintaining user profile", e);
        }
        user.setPassword(hashPassPw);
        return hashPassPw;
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
            List<UserDto> list = dao.fetchUserGroup(criteria);
            this.msg = "master list of groups were retrieved successfully";
            logger.info(this.msg);
            return list;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of groups";
            logger.error(this.msg);
            throw new UserApiException(this.msg, e);
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
        
        if (grp.getGroupId() > 0) {
            try {
                UserDto origRec = dao.fetchUserGroup(grp.getGroupId());
                Verifier.verifyNotNull(origRec);
                grp.setDateCreated(origRec.getDateCreated());
            }
            catch (VerifyException e) {
                this.msg = "User Group does not exists [group id=" + grp.getGroupId() + "]";
                throw new NotFoundException(this.msg, e);
            }
            catch (UserDaoException e) {
                this.msg = "Unable to verify the existence of User Group";
                throw new UserApiException(this.msg, e);
            }
        }
        
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
        }
    }

    @Override
    public void activate(String userName) throws UserApiException {
        UserDto user = null;
        try {
            user = dao.fetchUserProfile(userName);
            Verifier.verifyNotNull(user);
            user.setActive(1);
            dao.maintainUser(user);
        } catch (VerifyException e) {
            this.msg = "User cannot be activated due to profile cannot be found";
            throw new NotFoundException(this.msg, e);
        } catch (UserDaoException e) {
            this.msg = "Unable to activate user profile";
            throw new UserApiException(this.msg, e);
        }
    }

    @Override
    public void inActivate(String userName) throws UserApiException {
        UserDto user = null;
        try {
            user = dao.fetchUserProfile(userName);
            Verifier.verifyNotNull(user);
            user.setActive(1);
            dao.maintainUser(user);
        } catch (VerifyException e) {
            this.msg = "User cannot be deactivated due to profile cannot be found";
            throw new NotFoundException(this.msg, e);
        } catch (UserDaoException e) {
            this.msg = "Unable to deactivate user profile";
            throw new UserApiException(this.msg, e);
        }
    }


}
