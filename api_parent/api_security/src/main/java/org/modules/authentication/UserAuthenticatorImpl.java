package org.modules.authentication;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.roles.RoleDao;
import org.dao.roles.RoleDaoFactory;
import org.dao.user.UserDao;
import org.dao.user.UserDaoException;
import org.dao.user.UserDaoFactory;
import org.dto.CategoryDto;
import org.dto.UserDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;
import org.modules.SecurityConstants;
import org.modules.SecurityModuleException;
import org.modules.roles.RoleSecurityApiFactory;
import org.modules.roles.UserAppRoleApi;

import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DaoClient;
import com.api.security.User;
import com.api.security.authentication.web.LogoutException;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;
import com.api.web.security.RMT2SecurityToken;

/**
 * An implementation of the {@link Authenticator} interface that performs user
 * authentication and authorization.
 * 
 * @author rterrell
 * 
 */
class UserAuthenticatorImpl extends AbstractTransactionApiImpl implements Authenticator {

    private static final Logger logger = Logger.getLogger(UserAuthenticatorImpl.class);
    
    private static Map<String, RMT2SecurityToken> USER_TOKENS;
    

    /**
     * Create a UserAuthenticatorImpl using the specified application name.
     * 
     * @param appName
     */
    protected UserAuthenticatorImpl(String appName) {
        super(appName);
        logger.info("Authenticator is initialized by application name, " + appName);
        return;
    }

    /**
     * Create a AppRoleApiImpl using DaoClient instance that is intended
     * to be shared by another related application.
     * 
     * @param dao
     *            instance of {@link DaoClient}
     */
    protected UserAuthenticatorImpl(DaoClient dao) {
        super(SecurityConstants.APP_NAME, dao);
        logger.info("Authenticator is initialized using a shared DAO client");
    }
    
    @Override
    public void init() {
        super.init();
        if (UserAuthenticatorImpl.USER_TOKENS == null) {
            UserAuthenticatorImpl.USER_TOKENS = new Hashtable<String, RMT2SecurityToken>();    
        }
    }
    
    
    /**
     * Performs authentication for a the specified user using his/her login id
     * only.
     * <p>
     * Authentication is achieved by verifying whether or not the user's profile
     * is found in the list of security tokens managed internally by this
     * implementation. When the user is found in the list of tokens, the user's
     * application counter is incremented by one, which indicates the total
     * number applications currently active for the user.
     * <p>
     * This is typically used as a single sign on approach regarding user
     * authentication.
     * 
     * @param userName
     *            The user's login id
     * @return An instance of {@link RMT2SecurityToken} which represents the
     *         user's updated security token.
     * @throws AuthenticationException
     *             When the user is not logged on to any applications.
     */
    @Override
    public RMT2SecurityToken authenticate(String userName) throws AuthenticationException {
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new UsernameInvalidException("Username is required for Single Sign On authentication");
        }
        
        RMT2SecurityToken token;
        try {
            token = this.getSecurityToken(userName);
        } catch (SecurityTokenAccessException e) {
            token = null;
        }
        if (token == null) {
            throw new AuthenticationException("Single sign on authentication failed for user, " + userName);
        }
        token.getUser().incrementAppCount();
        // Update token properties with changes made to its User instance.
        token.update();
        return token;
    }

    /**
     * Authenticate user for the first time using basic login credentials,
     * <i>loginId</i> and <i>password</i>.
     * <p>
     * Upon successful authentication, a security token is created for the user
     * in which the user's application count is incremented by one and returned
     * to the caller.
     * 
     * @param userName
     *            The user's login id.
     * @param password
     *            The user's password.
     * @return An instance of {@link RMT2SecurityToken} which represents the
     *         user's security token.
     * @throws AuthenticationException
     *             User login failed or the login process experienced a system
     *             failure.
     * @throws UsernameInvalidException
     *             <i>userName</i> is null or invalid
     * @throws PasswordInvalidException
     *             <i>password</i> is null or invalid
     */
    @Override
    public RMT2SecurityToken authenticate(String userName, String password) throws AuthenticationException {
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new UsernameInvalidException("Username is required");
        }
        try {
            Verifier.verifyNotEmpty(password);
        } catch (VerifyException e) {
            throw new PasswordInvalidException("Password is required");
        }

        // logic to encrypt password before attempting to login
        String hashPassPw = null;
        try {
            hashPassPw = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(userName + password));
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException("Unable to encrypt password during authentication", e);
        }
        
        // Perform user login
        UserDto user = null;
        try {
            user = this.doLogin(userName, hashPassPw);
        } catch (NotFoundException | LoginFailedException e) {
            throw new AuthenticationException("User authentication failed due to invalid user id and/or password", e);
        }

        // Create security token and add to list of authenticated users.
        return this.doPostLogin(user);

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
     * @return The user's profile as an instance of {@link UserDto}
     * @throws DatabaseException
     *             password is incorrect or the user's loggedIn flag could not
     *             be applied to the database.
     * @throws NotFoundException
     *             <i>userName</i> is null or does not exist in the database.
     * @throws AuthenticationException
     * @throws CannotPersistException
     *             unable to update user's profile
     */
    private UserDto doLogin(String userName, String password) throws AuthenticationException {
        UserDto user = this.getUser(userName);
        try {
            Verifier.verifyNotNull(user);
        }
        catch (VerifyException e) {
            throw new NotFoundException("User profile does not exist for user, " + userName);
        }

        // Get decrypted password
        String origPasswordDecrypt = user.getPassword();
        if (!origPasswordDecrypt.equalsIgnoreCase(password)) {
            throw new LoginFailedException("Password is incorrect");
        }

        // Password has to be reset each time the user logs in to prevent
        // over encryption
        user.setPassword(password);
        // increment total logons
        int logons = user.getTotalLogons();
        user.setTotalLogons(++logons);
        
        return user;
    }

    /**
     * Updates the user profile and returns a user security token
     * 
     * @param userProfile
     * @return instance of {@link RMT2SecurityToken}
     */
    private RMT2SecurityToken doPostLogin(UserDto userProfile) throws AuthenticationException {
        // At this point, the user is authenticated!
        User tokenUser = Rmt2OrmDtoFactory.getUserInstance(userProfile);

        // Associate application roles with user
        RoleDao roleDao = null;
        try {
            roleDao = RoleDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
            List<CategoryDto> roleList = roleDao.fetchUserAppRole(userProfile.getUsername());
            for (CategoryDto role : roleList) {
                tokenUser.addRole(role.getAppRoleCode());
            }
        } catch (SecurityDaoException e) {
            throw new AuthenticationException("Unable to retrieve user's application roles during authentication", e);
        }

        // Update the authenticated user's profile
        try {
            UserDao dao = UserDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
            dao.setDaoUser(userProfile.getUsername());
            int rows = dao.maintainUser(userProfile);
            logger.info("User, " + userProfile.getUsername() + ", was successfully logged in effecting " + rows
                    + " rows(s)");
        } catch (UserDaoException e) {
            throw new AuthenticationException("Unable to update user's profile durig authentication", e);
        }

        // Create security token and add to list of authenticated users.
        RMT2SecurityToken token = new RMT2SecurityToken();
        tokenUser.incrementAppCount();
        token.update(tokenUser);
        UserAuthenticatorImpl.USER_TOKENS.put(tokenUser.getLoginId(), token);
        return token;
    }

    /**
     * 
     * @param userName
     * @return
     * @throws AuthenticationException
     */
    private UserDto getUser(String userName) throws AuthenticationException {
        UserDao dao = null;
        List<UserDto> userList = null;
        try {
            dao = UserDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
            dao.setDaoUser(userName);
            UserDto userCriteria = Rmt2OrmDtoFactory.getNewUserInstance();
            userCriteria.setUsername(userName);
            userList = dao.fetchUserProfile(userCriteria);
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            }
            return null;
        } catch (UserDaoException e) {
            throw new AuthenticationException("Unable to retrieve user's profile during authentication", e);
        }
    }
    
    /**
     * Queries the in memory security token Map to determines if a user is
     * currently signed on to the system.
     * 
     * @param userName
     *            The user's login id.
     * @return true when user is found to be logged in and false when user is
     *         not found in the list of authenticated users or a problem
     *         occurred fetching the user
     */
    @Override
    public boolean isAuthenticated(String userName) {
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new UsernameInvalidException("Username is required for authentication check");
        }
        
        RMT2SecurityToken token;
        try {
            token = this.getSecurityToken(userName);
        } catch (SecurityTokenAccessException e) {
            token = null;
        }
        return (token != null);
    }

    /**
     * Returns a security token for a given user.
     * <p>
     * The source of the lookup is an internal Map of authenticated users in the
     * form of {@link RMT2SecurityToken} objects.
     * 
     * @param userName
     *            The user's login id.
     * @return an instance of {@link RMT2SecurityToken} or null when user is not
     *         logged into system.
     * @throws SecurityTokenAccessException
     *             when <i>loginId</i> is null or the value returned is not of
     *             type {@link RMT2SecurityToken}
     */
    protected RMT2SecurityToken getSecurityToken(String userName) throws SecurityTokenAccessException {
        try {
            RMT2SecurityToken token = UserAuthenticatorImpl.USER_TOKENS.get(userName);
            return token;
        } catch (Exception e) {
            throw new SecurityTokenAccessException(e);
        }
    }

    /**
     * Logs a user out of the system and returns the count of remaining
     * applications the is signed on to.
     * <p>
     * This method provides two ways the user can achieve logging out: <i>hard
     * sign off</i> or <i>soft sign off</i>. A hard sign off is needed when the
     * user is signed on to only one application, and, subsequetly, the user's
     * profile is updated in the database to indicate an official user system
     * logoff. A soft sign off is usually employed when the user is signed on to
     * multiple applications and conducts a logoff operation. In this case the
     * logoff operation will gain access to the user's security token and simply
     * decrement the application count for the user.
     * 
     * @param userName
     *            The user's login id.
     * @return The number of remianing applications the user was signed on.
     * @throws LogoutException
     *             Error obtaining the user's security token
     */
    @Override
    public int logout(String userName) throws LogoutException {
        try {
            Verifier.verifyNotEmpty(userName);
        } catch (VerifyException e) {
            throw new UsernameInvalidException("Username is required for logout operation");
        }
        
        RMT2SecurityToken token = null;
        try {
            token = this.getSecurityToken(userName);
        } catch (SecurityTokenAccessException e) {
            throw new LogoutException(e);
        }
        // User is not logged on to any applications.
        if (token == null) {
            return 0;
        }

        // Since the user is signed on to multiple applications, decrement
        // application count and return to client.
        if (token.getAppCount() > 1) {
            token.getUser().decrementAppCount();
            token.update();
        }
        else {
            // Remove user token
            try {
                token.getUser().decrementAppCount();
                token = this.invalidateUserToken(userName);
                logger.info("User, " + userName + ", was logged out successfully!!!");
            } catch (SecurityTokenAccessException e) {
                throw new LogoutException("An error occurred attempting to invalidate security token for user, " + userName, e);
            }    
        }
        
        return token.getAppCount();
    }

    /**
     * Invalidates the user's security token by removing it from the Map of
     * valid user tokens.
     * <p>
     * All properties of the returned token will be reset with the exception of
     * the userName property
     * 
     * @param userName
     * @return an instance of {@link RMT2SecurityToken} which is the token
     *         removed or null when user has already been invalidated.
     * @throws SecurityTokenAccessException
     */
    @Override
    public RMT2SecurityToken invalidateUserToken(String userName) throws SecurityTokenAccessException {
        RMT2SecurityToken token = null;
        try {
            token = UserAuthenticatorImpl.USER_TOKENS.remove(userName);
        } catch (Exception e) {
            throw new SecurityTokenAccessException(e);
        }
        
        // Perform updates
        if (token == null) {
            return null;
        }
        token.getUser().setBirthDate(null);
        token.getUser().setEmail(null);
        token.getUser().setPassword(null);
        token.getUser().setPersonId(0);
        token.getUser().setRaceId(0);
        token.getUser().setSsn(null);
        token.getUser().setFirstname(null);
        token.getUser().setLastname(null);
        token.getUser().setUserDescription(null);
        token.getUser().setUid(0);
        token.getUser().getRoles().clear();
        token.getUser().setRoles(null);
        token.getUser().setShortname(null);
        token.getUser().setMaidenname(null);
        token.getUser().setMidname(null);
        token.getUser().setMaritalStatus(0);
        token.getUser().setGenderId(0);
        token.getUser().setGeneration(null);
        token.getUser().setAppCount(0);
        
        // Update token properties that were reset prior to returning the invalid token.
        token.update();
        return token;
    }
    
    /**
     * Verifies if the user has authorization to access a resource based on one
     * or more of the required roles specified.
     * <p>
     * This implementation requires:
     * <ol>
     * <li>The user is valid and not null.</li>
     * <li>The user is granted one or more roles.</li>
     * <li>The required roles collection is not empty or null.</li>
     * <li>One or more of the user's roles are found in the list of required
     * roles.</li>
     * </ol>
     * 
     * @param userName
     *            The user's login id.
     * @param requiredRoles
     *            A List of String objects representing the roles required by
     *            the target resource the user wishes to access.
     * @return <i>true></i> when authorized and <i>false</i> otherwise.           
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    @Override
    public boolean authorize(String userName, List<String> requiredRoles) throws AuthorizationException {
        try {
            Verifier.verifyNotEmpty(userName);
        }
        catch (VerifyException e) {
            this.msg = "Username is required for authorziation";
            throw new AuthorizationException(this.msg, e);
        }
        
        try {
            Verifier.verifyNotEmpty(requiredRoles);
        }
        catch (VerifyException e) {
            this.msg = "A list of required application roles are required";
            throw new AuthorizationException(this.msg, e);
        }
        
        List<String> userAppRoles = null;
        try {
            userAppRoles = this.getUserAppRoleCodes(userName);
        }
        catch (CannotRetrieveException e) {
            throw new AuthorizationException("Unable to authorize user, " + userName
                            + ", due to user application roles are unavailable", e);
        }
        
        try {
            Verifier.verifyNotEmpty(userAppRoles);
        }
        catch (VerifyException e) {
            this.msg = "Unable to locate any User Application Roles for user, " + userName;
            throw new AuthorizationException(this.msg, e);
        }
        
        boolean authorized = this.isAuthorized(requiredRoles, userAppRoles);
        return authorized;
    }

    /**
     * Returns a list of user application roles.
     * 
     * @param userName
     *            the login id of the user to obtain application roles for.
     * @return a List of String representing the user's application roles.
     * @throws CannotRetrieveException
     *             Error fetching the application roles.
     */
    private List<String> getUserAppRoleCodes(String userName) throws CannotRetrieveException {
        UserAppRoleApi rolesApi = null;
        List<CategoryDto> roleList = null;
        try {
            rolesApi = RoleSecurityApiFactory.createUserAppRoleApi(SecurityConstants.APP_NAME);
            CategoryDto criteria = Rmt2OrmDtoFactory.getUserAppRoleDtoInstance(null, null);
            criteria.setUsername(userName);
            roleList = rolesApi.get(criteria);
        } catch (SecurityModuleException e) {
            throw new CannotRetrieveException("Unable to retrieve user application roles for " + userName, e);
        }

        if (roleList == null) {
            return null;
        }
        // Package roles into List of application role codes.
        List<String> userRoles = new ArrayList<String>();
        for (CategoryDto role : roleList) {
            userRoles.add(role.getAppRoleCode());
        }
        return userRoles;
    }

    /**
     * Determines user authorization by matching one or more user application
     * roles with the required roles.
     * 
     * @param userName
     *            The login id of the user that is to be authorized.
     * @param requiredRoles
     *            A List of Strings values representing the required roles in
     *            which one or more of the user's roles must match in order to
     *            be considered authorized.
     * @param userRoles
     *            A List of String values representing the roles assigned to the
     *            user targeted for authorization.
     * @return true when the user is found to be authorized and fale when the
     *         user is not authorized.
     * @throws AuthorizationException
     *             Either <i>requiredRoles</i> and <i>userRoles</i> are found to
     *             be null or contain no elements.
     */
    private boolean isAuthorized(List<String> requiredRoles, List<String> userRoles) throws AuthorizationException {
        // Match up the roles.
        int totalMatches = 0;
        for (String userRole :  userRoles) {
            if (requiredRoles.contains(userRole)) {
                totalMatches++;
            }
        }
        return (totalMatches > 0);
    }
    
}
