package org.dao.authentication;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.SecurityDaoImpl;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.modules.authentication.PasswordInvalidException;
import org.modules.authentication.UsernameInvalidException;

import com.RMT2Constants;
import com.api.persistence.CannotPersistException;
import com.api.persistence.PersistenceClient;
import com.api.security.User;

/**
 * An RMT2 ORM database implementation of the {@link AuthenticationDao}
 * interface which functions to manage user security and security application
 * lookup data.
 * 
 * @author roy.terrell
 * @deprecated not needed any more
 * 
 */
class Rmt2OrmAuthenticationDaoImpl extends SecurityDaoImpl implements
        AuthenticationDao {

    private static final Logger logger = Logger
            .getLogger(Rmt2OrmAuthenticationDaoImpl.class);

    /**
     * Default Constructor. Constructor begins the initialization of the
     * DatabaseConnectionBean at the acestor level.
     * 
     */
    protected Rmt2OrmAuthenticationDaoImpl() {
        super();
    }

    /**
     * Default Constructor. Constructor begins the initialization of the
     * DatabaseConnectionBean at the acestor level.
     * 
     * @param appName
     *            application name
     */
    protected Rmt2OrmAuthenticationDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2OrmAuthenticationDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    protected Rmt2OrmAuthenticationDaoImpl(PersistenceClient client) {
        super(client);
    }
    
    /**
     * Verifies the user exists and that the <i>userId</i> and <i>password</i>
     * matches the credentials stored as part of the user's profile.
     * <p>
     * This method returns the user's profile including any applicable roles.
     * 
     * @param userName
     *            The login id of the user.
     * @param password
     *            The user's passowrd.
     * @return The user's profile as an instance of {@link User}
     * @throws DatabaseException
     *             password is incorrect or the user's loggedIn flag could not
     *             be applied to the database.
     * @throws NotFoundException
     *             <i>userName</i> is null or does not exist in the database.
     */
    public User loginUser(String userName, String password) throws SecurityDaoException {
        if (userName == null) {
            throw new UsernameInvalidException("User is invalid");
        }

        UserDto user = this.getUserByUsername(userName);
        if (user == null) {
            throw new UsernameInvalidException("User profile does not exist for user, " + userName);
        }

        // Get decrypted password
        String origPasswordDecrypt = user.getPassword();
        if (!origPasswordDecrypt.equalsIgnoreCase(password)) {
            throw new PasswordInvalidException("Password is incorrect");
        }

        // At this point, the user is authenticated!
        User u = Rmt2OrmDtoFactory.getUserInstance(user);

        // Associate application roles with user
        RoleDao roleDao = null;
        try {
            roleDao = RoleDaoFactory.createRmt2OrmDao();
            List<CategoryDto> roleList = roleDao.fetchUserAppRole(userName);
            for (CategoryDto role : roleList) {
                u.addRole(role.getAppRoleCode());
            }
        } catch (SecurityDaoException e) {
            throw new SecurityDaoException("Unable to retrieve user's application roles during authentication",
                    e);
        }

        // Update the authenticated user's profile
        UserDaoFactory userFactory = new UserDaoFactory();
        UserDao dao = null;
        try {
            // Password has to be reset each time the user logs in to prevent
            // over encryption
            user.setPassword(password);
            // increment total logons
            int logons = user.getTotalLogons();
            user.setTotalLogons(++logons);
            // perform update
            dao = userFactory.createRmt2OrmDao();
            dao.setDaoUser(this.getDaoUser());
            int rows = dao.maintainUser(user);
            logger.info("User, " + userName
                    + ", was successfully logged in effecting " + rows
                    + " rows(s)");
            return u;
        } catch (UserDaoException e) {
            throw new CannotPersistException(
                    "Unable to update user's profile durig authentication", e);
        }
    }

    private UserDto getUserByUsername(String userName) {
        UserDaoFactory userFactory = new UserDaoFactory();
        UserDao dao = null;
        List<UserDto> userList = null;
        try {
            dao = userFactory.createRmt2OrmDao();
            dao.setDaoUser(this.getDaoUser());
            UserDto userCriteria = Rmt2OrmDtoFactory.getNewUserInstance();
            userCriteria.setUsername(userName);
            userList = dao.fetchUserProfile(userCriteria);
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            }
            return null;
        } catch (UserDaoException e) {
            throw new SecurityDaoException("Unable to retrieve user's profile during authentication", e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dao.authentication.AuthenticationDao#logoutUser(java.lang.String)
     */
    @Override
    public void logoutUser(String userName) throws CannotPersistException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    // /* (non-Javadoc)
    // * @see
    // org.dao.authentication.AuthenticationDao#logoutUser(java.lang.String)
    // */
    // @Override
    // public void logoutUser(String userName) throws CannotPersistException {
    // // Fetch the user's profile
    // UserGroupMaintApiFactory userFactory = new UserGroupMaintApiFactory();
    // UserApi userApi = userFactory.createApi();
    // userApi.setApiUser(this.getDaoUser());
    // UserDto user;
    // try {
    // user = userApi.getUser(userName);
    // }
    // catch (UserApiException e) {
    // throw new
    // SecurityDaoException("Unable to retrieve user's profile during logout",
    // e);
    // }
    //
    // // Decrypt password derived from the database ans assign to bean instacne
    // // so to not over encrypt when applying changes to the database.
    // String origPasswordDecrypt = user.getPassword();
    // user.setPassword(origPasswordDecrypt);
    //
    // // If this is the last application the user has accessed, set logged in
    // // flag to 0 (false) which indicates that the user is truly logged off.
    // int logons = user.getTotalLogons();
    // if (--logons <= 0) {
    // user.setLoggedIn(0);
    // user.setTotalLogons(0);
    // }
    // else {
    // user.setLoggedIn(1);
    // user.setTotalLogons(logons);
    // }
    //
    // // Update the user's profile by indicating that he is logged off.
    // this.client.beginTrans();
    // try {
    // userApi.updateUser(user);
    // }
    // catch (UserApiException e) {
    // throw new CannotPersistException("Unable to logout user, " + userName,
    // e);
    // }
    // finally {
    // userApi = null;
    // }
    // }

}