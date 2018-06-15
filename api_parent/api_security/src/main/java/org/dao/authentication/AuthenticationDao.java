package org.dao.authentication;

import org.dao.SecurityDaoException;

import com.api.persistence.DaoClient;
import com.api.security.User;

/**
 * Contract for performing user authentication operations.
 * 
 * @author rterrell
 * 
 */
public interface AuthenticationDao extends DaoClient {

    /**
     * Verify user authenticity using basic login credentials
     * 
     * @param loginId
     *            The login id of the user.
     * @param password
     *            The user's passowrd.
     * @return the user's profile as an instance of {@link User}
     * @throws SecurityDaoException
     *             <ol>
     *             <li>loginId is missing or invalid</li>
     *             <li>the password is incorrect.</li>
     *             <li>general database access error</li>
     *             </ol>
     * 
     */
    User loginUser(String loginId, String password) throws SecurityDaoException;

    /**
     * Log user off the system.
     * 
     * @param loginId
     *            The login id of the user.
     * @throws SecurityDaoException
     * @throws NotFoundException
     *             <i>userid</i> does not exist in the database.
     */
    void logoutUser(String loginId) throws SecurityDaoException;

}