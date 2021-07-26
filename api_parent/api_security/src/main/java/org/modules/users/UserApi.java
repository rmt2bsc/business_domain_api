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
     * Get all user groups.
     * 
     * @param group instance of {@link UserDto}
     * @return a List of {@link UserDto} objects containing the group data.
     * 
     * @throws UserApiException
     */
    List<UserDto> getGroup(UserDto group) throws UserApiException;

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
     * Activate user
     * 
     * @param userName
     *            the user name
     * @throws UserApiException
     */
    void activate(String userName) throws UserApiException;
    
    /**
     * Deactivate user
     * 
     * @param userName
     *            the user name
     * @throws UserApiException
     */
    void inActivate(String userName) throws UserApiException;

    /**
     * Changes the user's password.
     * 
     * @param userName
     *            the user's username or network id
     * @param newPassword
     *            the new password
     * @throws UserApiException
     */
    void changePassword(String userName, String newPassword) throws UserApiException;
}
