package org.modules.authentication;

import java.util.List;

import com.api.foundation.TransactionApi;
import com.api.security.authentication.web.LogoutException;
import com.api.web.security.RMT2SecurityToken;

/**
 * This interface provides the basic methods needed to authenticate and
 * authorize users. This contract provides a way to develop an application
 * independent of the underlying authentication technology.
 * 
 * @author roy.terrell
 * 
 */
public interface Authenticator extends TransactionApi {

    /**
     * Authenticate user without password verification pending the user is
     * actively signed on to one or more applications.
     * <p>
     * This is typically used as a single sign on approach regarding user
     * authentication.
     * 
     * @param userName
     *            The user's unique system identifier
     * @return An instance of {@link RMT2SecurityToken} which represents the
     *         user's security token.
     * @throws AuthenticationException
     *             There are no actvie applications in which the user is logged
     *             onto.
     */
    RMT2SecurityToken authenticate(String userName) throws AuthenticationException;

    /**
     * Authenticate a user for the first time using basic login credentials,
     * <i>loginId</i> and <i>password</i>.
     * <p>
     * This method is typically employed when the user is not actively signed on
     * to any applications.
     * 
     * @param userName
     *            The user's unique system identifier
     * @param password
     *            The user's password.
     * @return An instance of {@link RMT2SecurityToken} which represents the
     *         user's security token.
     * @throws AuthenticationException
     *             The user does not exist or the password is incorrect.
     */
    RMT2SecurityToken authenticate(String userName, String password) throws AuthenticationException;

    /**
     * Determines if a user is currently signed on to the system.
     * 
     * @param userName
     *            The user's login id.
     * @return true when user is found to be logged in and false when user has
     *         not logged in.
     */
    boolean isAuthenticated(String userName);

    /**
     * Returns a security token for a given user.
     * 
     * @param userName
     *            The user's login id.
     * @return an instance of {@link RMT2SecurityToken} or null when user is not
     *         logged into system.
     * @throws SecurityTokenAccessException
     */
    RMT2SecurityToken getSecurityToken(String userName) throws SecurityTokenAccessException;

    /**
     * Log specified user out of the system.
     * 
     * @param userName
     *            The user's unique system identifier
     * @return The number of remianing applications the user was signed on.
     * @throws LogoutException
     */
    int logout(String userName) throws LogoutException;

    /**
     * Determines if the user possesses one or more of the required roles. The
     * implementation must, first, determine if the user has been authenticated,
     * and ,secondly, verify the user possesses one or more of the the required
     * roles.
     * 
     * @param userName
     *            The user's login id.
     * @param requiredRoles
     *            A List of String objects representing the roles required by
     *            the target resource the user wishes to access.
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    void authorize(String userName, List<String> requiredRoles) throws AuthorizationException, AuthenticationException;

    /**
     * Sets the login count of the specified user to zero.
     * 
     * @param userName
     *            the login id of the user.
     * @return true when count is successfully reset and false otherwise.
     * @throws SecurityTokenAccessException
     */
    boolean resetLoginCount(String userName) throws SecurityTokenAccessException;
}
