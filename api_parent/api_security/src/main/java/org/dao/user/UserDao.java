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
     * Find user by UID
     * 
     * @param uid
     *            the unique identifier of the user
     * @return An instance of {@link UserDto}
     * @throws UserDaoException
     */
    UserDto fetchUserProfile(int uid) throws UserDaoException;
    
    /**
     * Find user by user name
     * 
     * @param userName
     *            the user name
     * @return An instance of {@link UserDto}
     * @throws UserDaoException
     */
    UserDto fetchUserProfile(String userName) throws UserDaoException;
    
    /**
     * Find user using custom criteria.
     * 
     * @param user
     *            an instance of {@link UserDto} containing property values used
     *            to build selection criteria.
     * @return a List of {@link UserDto} objects or null when no data is found.
     * @throws UserDaoException
     */
    List<UserDto> fetchUserProfile(UserDto user) throws UserDaoException;

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
     * Fetch a single user group
     * 
     * @param grpId
     * @return An instance of {@link UserDto}
     * @throws UserDaoException
     */
    UserDto fetchUserGroup(int grpId) throws UserDaoException;
    
    /**
     * Fetch user groups using custom selection criteria
     * 
     * @param group
     *            an instance of {@link UserDto} containing the group data.
     * @return a List of {@link UserDto} objects containing the group data or
     *         null if no data is found.
     * 
     * @throws UserDaoException
     */
    List<UserDto> fetchUserGroup(UserDto group) throws UserDaoException;

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
