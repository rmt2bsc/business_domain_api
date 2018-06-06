package org.dao.user;

import java.util.List;

import org.dto.UserDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data regarding the User module of the
 * Security API.
 * 
 * @author rterrell
 * 
 */
public interface UserDao extends DaoClient {

    /**
     * Find user using custom criteria.
     * 
     * @param user
     *            an instance of {@link UserDto} containing property values used
     *            to build selection criteria.
     * @return a List of {@link UserDto} objects or null when no data is found.
     * @throws UserDaoException
     */
    List<UserDto> fetchUser(UserDto user) throws UserDaoException;

    /**
     * Create or update a User object. Changes should persist to a specific
     * external data source.
     * 
     * @param user
     *            an instance of {@link UserDto}
     * @return int
     * @throws UserDaoException
     */
    int maintainUser(UserDto user) throws UserDaoException;

    /**
     * Delete user using internal unique identifier.
     * 
     * @param uid
     *            the user's internal unique identifier.
     * @return the total number of rows effected.
     * @throws UserDaoException
     */
    int deleteUser(int uid) throws UserDaoException;

    /**
     * Set the activate flag of a user to true. Results should be persisted via
     * an external data source.
     * 
     * @param userName
     *            UserLogin
     * @return int
     * @throws UserDaoException
     */
    int activateUser(String userName) throws UserDaoException;

    /**
     * Set the activate flag of a user to false. Results should be persisted via
     * an external data source.
     * 
     * @param userName
     *            UserLogin
     * @return int
     * @throws UserDaoException
     */
    int inActivateUser(String userName) throws UserDaoException;

    /**
     * Return all user groups.
     * 
     * @param group
     *            an instance of {@link UserDto} containing the group data.
     * @return a List of {@link UserDto} objects containing the group data or
     *         null if no data is found.
     * 
     * @throws UserDaoException
     */
    List<UserDto> fetchGroup(UserDto group) throws UserDaoException;

    /**
     * Create or update a User Group object.
     * <p>
     * Changes should persist to a specific external data source.
     * 
     * @param grp
     *            an instance of {@link UserDto} containing the group data.
     * @return int
     * 
     * @throws UserDaoException
     */
    int maintainGroup(UserDto grp) throws UserDaoException;

    /**
     * Deletes a user group from the system using the group id.
     * 
     * @param grpId
     *            The id of the group that is to be delete.
     * @return an int value acting as the total number of targets affected.
     * @throws UserDaoException
     */
    int deleteGroup(int grpId) throws UserDaoException;
}
