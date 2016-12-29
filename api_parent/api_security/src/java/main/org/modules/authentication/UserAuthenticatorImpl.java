package org.modules.authentication;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.dao.SecurityDaoException;
import org.dao.authentication.AuthenticationDao;
import org.dao.authentication.AuthenticationDaoFactory;
import org.dto.CategoryDto;
import org.modules.SecurityModuleException;
import org.modules.roles.RoleSecurityApiFactory;
import org.modules.roles.UserAppRoleApi;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.CannotRetrieveException;
import com.api.security.User;
import com.api.security.authentication.web.LogoutException;
import com.api.web.security.RMT2SecurityToken;

/**
 * An implementation of the {@link Authenticator} interface that .
 * 
 * @author rterrell
 * 
 */
class UserAuthenticatorImpl extends AbstractTransactionApiImpl implements
        Authenticator {

    private static Map<String, RMT2SecurityToken> USER_TOKENS;

    /**
     * Create a UserAuthenticatorDatabaseImpl object.
     */
    protected UserAuthenticatorImpl() {
        UserAuthenticatorImpl.USER_TOKENS = new Hashtable<String, RMT2SecurityToken>();
        return;
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
    public RMT2SecurityToken authenticate(String userName)
            throws AuthenticationException {
        RMT2SecurityToken token;
        try {
            token = this.getSecurityToken(userName);
        } catch (SecurityTokenAccessException e) {
            token = null;
        }
        if (token == null) {
            throw new AuthenticationException(
                    "Single sign on authentication failed for user, "
                            + userName
                            + ".  Must provide user id and password in order to authenticate.");
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
     * to the caller. If by chance the user is already logged on, then the
     * original just security token is returned without incrementing the user's
     * application count.
     * 
     * @param userName
     *            The user's login id.
     * @param password
     *            The user's password.
     * @return An arbitrary object that represents the authentication results.
     * @throws AuthenticationException
     *             For any case where the user's login attempt fails.
     */
    @Override
    public RMT2SecurityToken authenticate(String userName, String password)
            throws AuthenticationException {
        // If user has already signed on, just return security token without
        // incrementing application count.
        RMT2SecurityToken token = null;
        try {
            token = this.getSecurityToken(userName);
            if (token != null) {
                return token;
            }
        } catch (SecurityTokenAccessException e) {
            throw new AuthenticationException(e);
        }

        User user = null;
        AuthenticationDao dao = null;
        try {
            AuthenticationDaoFactory f = new AuthenticationDaoFactory();
            // dao = f.createRmt2OrmDao();
            dao = f.createLdapDao();
            dao.setDaoUser(this.apiUser);
            user = dao.loginUser(userName, password);
        } catch (SecurityDaoException e) {
            this.msg = "A data access error occurred while attempting to authenticate user";
            throw new AuthenticationException(this.msg, e);
        } finally {
            dao.close();
        }

        // Create security token and add to list of authenticated users.
        token = new RMT2SecurityToken();
        user.incrementAppCount();
        token.update(user);
        UserAuthenticatorImpl.USER_TOKENS.put(user.getLoginId(), token);
        return token;

    }

    /**
     * Queries the in memory security token Map to determines if a user is
     * currently signed on to the system.
     * 
     * @param loginId
     *            The user's login id.
     * @return true when user is found to be logged in and false when user is
     *         not found in the list of authenticated users or a problem
     *         occurred fetching the user
     */
    @Override
    public boolean isAuthenticated(String loginId) {
        try {
            return (UserAuthenticatorImpl.USER_TOKENS.get(loginId) != null);
        } catch (Exception e) {
            return false;
        }
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
    @Override
    public RMT2SecurityToken getSecurityToken(String userName)
            throws SecurityTokenAccessException {
        try {
            RMT2SecurityToken token = UserAuthenticatorImpl.USER_TOKENS
                    .get(userName);
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
            return token.getAppCount();
        }
        return 0;
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
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    @Override
    public void authorize(String userName, List<String> requiredRoles)
            throws AuthorizationException, AuthenticationException {
        List<String> userRoles = this.getUserRoles(userName);
        boolean authorized = this.isAuthorized(userName, requiredRoles,
                userRoles);
        if (authorized) {
            return;
        }
        else {
            this.msg = "User does not possess the appropriate authorization";
            throw new AuthorizationException(this.msg);
        }
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
    private List<String> getUserRoles(String userName)
            throws CannotRetrieveException {
        UserAppRoleApi rolesApi = null;
        List<CategoryDto> roleList = null;
        try {
            RoleSecurityApiFactory roleFactory = new RoleSecurityApiFactory();
            rolesApi = roleFactory.createUserAppRoleApi();
            roleList = rolesApi.get(userName);
        } catch (SecurityModuleException e) {
            throw new SecurityDaoException(
                    "Unable to retrieve user's application roles during user authorization",
                    e);
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
     * Determines user authorization by matching one or more required roles with
     * the user's roles.
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
    private boolean isAuthorized(String userName, List<String> requiredRoles,
            List<String> userRoles) throws AuthorizationException {
        if (requiredRoles == null || requiredRoles.size() == 0) {
            this.msg = "The list of required roles are invalid or null";
            throw new AuthorizationException(this.msg);
        }
        if (userRoles == null || userRoles.size() == 0) {
            this.msg = "The user, " + userName
                    + ", has not been granted any application roles";
            throw new AuthorizationException(this.msg);
        }

        // Match up the roles.
        List<String> matches = this.getMatchingRoles(userRoles, requiredRoles);
        return (matches.size() > 0);
    }

    /**
     * Determines if the user possesses one or more required roles and builds a
     * list every user role that matches with a required role.
     * 
     * @param userRoles
     *            The roles assigned to the user.
     * @param requiredRoles
     *            The roles the user must authorized for.
     * @return A List of roles in code name form that match one or more of the
     *         required roles.
     */
    private List<String> getMatchingRoles(List<String> userRoles,
            List<String> requiredRoles) {
        List<String> matchedRoles = new ArrayList<String>();
        for (int ndx = 0; ndx < requiredRoles.size(); ndx++) {
            String reqRole = (String) requiredRoles.get(ndx);
            for (int ndx2 = 0; ndx2 < userRoles.size(); ndx2++) {
                String userRole = (String) userRoles.get(ndx2);
                if (userRole.trim().equalsIgnoreCase(reqRole.trim())) {
                    matchedRoles.add(reqRole.trim());
                }
            }
        }
        return matchedRoles;
    }

    /**
     * Sets the application login count of the user's security token to zero.
     * 
     * @param userName
     *            the login id of the user.
     * @return true when count is successfully reset and false otherwise.
     * @throws SecurityTokenAccessException
     */
    @Override
    public boolean resetLoginCount(String userName)
            throws SecurityTokenAccessException {
        RMT2SecurityToken token;
        token = this.getSecurityToken(userName);
        if (token == null) {
            return false;
        }
        token.getUser().setAppCount(0);
        // Update token properties with changes made to its User instance.
        token.update();
        return true;
    }

}
