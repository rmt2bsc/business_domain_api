package org.modules.authentication;

import java.util.List;

import org.dto.UserDto;

/**
 * Contract for an authenticated user entity.
 * 
 * @author rterrell
 * 
 */
public interface AuthenticatedUser extends UserDto {

    /**
     * @return the roles
     */
    List<String> getRoles();

    /**
     * @param roles
     *            the roles to set
     */
    void setRoles(List<String> roles);

    /**
     * Returns the total number of appliations the user is signed on.
     * 
     * @return the total number of appliations the user is signed on.
     */
    int getAppCount();

    /**
     * Sets the total number of applications the user is signed on.
     * 
     * @param count
     *            the application count.
     */
    void setAppCount(int count);

    /**
     * Increments the total number of applications a user is signed on by 1.
     */
    void incrementAppCount();

    /**
     * Decrements the total number of applications a user is signed on by 1.
     */
    void decrementAppCount();

    /**
     * 
     * @param role
     */
    void addRole(String role);
}