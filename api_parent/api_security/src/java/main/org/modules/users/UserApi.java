package org.modules.users;

import java.util.List;

import org.dto.UserDto;

import com.api.foundation.TransactionApi;

/**
 * Contract for the User and Group module of the Security API
 * 
 * @author rterrell
 * 
 */
public interface UserApi extends TransactionApi {

    /**
     * Get all users.
     * 
     * @return a List of {@link UserDto} objects containing the user data.
     * 
     * @throws UserApiException
     */
    List<UserDto> getUser() throws UserApiException;

    /**
     * Get user using primary key id.
     * 
     * @param uid
     *            A unique id identifying the user.
     * @return An instance of {@link UserDto} containing the user data.
     * @throws UserApiException
     */
    UserDto getUser(int uid) throws UserApiException;

    /**
     * Get user by user name (Login id).
     * 
     * @param userName
     *            the user login id.
     * @return an instance of {@link UserDto}
     * @throws UserApiException
     */
    UserDto getUser(String userName) throws UserApiException;

    /**
     * Get user using custom criteria.
     * 
     * @param user
     *            an instance of {@link UserDto} containing property values used
     *            to build selection criteria.
     * @return a List of {@link UserDto} objects or null when no data is found.
     * @throws UserApiException
     */
    List<UserDto> getUser(UserDto user) throws UserApiException;

    /**
     * Add or modify a User object.
     * 
     * @param user
     *            an instance of {@link UserDto}
     * @return int
     * @throws UserApiException
     */
    int updateUser(UserDto user) throws UserApiException;

    /**
     * Delete user using internal unique identifier.
     * 
     * @param uid
     *            the user's internal unique identifier.
     * @return the total number of rows effected.
     * @throws UserApiException
     */
    int deleteUser(int uid) throws UserApiException;

    /**
     * Delete a user using user name.
     * 
     * @param userName
     *            the user's login id.
     * @return the total number of rows effected.
     * @throws UserApiException
     */
    int deleteUser(String userName) throws UserApiException;

    /**
     * Get all user groups.
     * 
     * @return a List of {@link UserDto} objects containing the group data.
     * 
     * @throws UserApiException
     */
    List<UserDto> getGroup() throws UserApiException;

    /**
     * Get user group using primary key id.
     * 
     * @param grpId
     *            A unique id identifying the user group.
     * @return An instance of {@link UserDto} containing the user group data.
     * @throws UserApiException
     */
    UserDto getGroup(int grpId) throws UserApiException;

    /**
     * Get user group by name
     * 
     * @param grpName
     *            The group's name
     * @return An instance of {@link UserDto} containing the user group data.
     * @throws UserApiException
     */
    UserDto getGroup(String grpName) throws UserApiException;

    /**
     * Add or modify a User Group object.
     * 
     * @param grp
     *            an instance of {@link UserDto} containing the group data.
     * @return int
     * 
     * @throws UserApiException
     */
    int updateGroup(UserDto grp) throws UserApiException;

    /**
     * Deletes a user group using its unique identifier
     * 
     * @param grpId
     *            The id of the group that is to be delete.
     * @return an int value acting as the total number of targets affected.
     * @throws UserApiException
     */
    int deleteGroup(int grpId) throws UserApiException;

    /**
     * Deletes a user group using its name.
     * 
     * @param grpName
     *            The name of the group
     * @return an int value acting as the total number of targets affected.
     * @throws UserApiException
     */
    int deleteGroup(String grpName) throws UserApiException;
}
