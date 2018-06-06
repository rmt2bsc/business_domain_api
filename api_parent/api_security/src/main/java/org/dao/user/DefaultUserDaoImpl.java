package org.dao.user;

import java.util.List;

import org.dao.SecurityDaoImpl;
import org.dto.UserDto;

/**
 * A common implementation of UserDao interface
 * 
 * @author Roy Terrell
 * 
 */
public class DefaultUserDaoImpl extends SecurityDaoImpl implements UserDao {

    /**
     * 
     */
    public DefaultUserDaoImpl() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#close()
     */
    @Override
    public void close() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#getDaoUser()
     */
    @Override
    public String getDaoUser() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.persistence.DaoClient#setDaoUser(java.lang.String)
     */
    @Override
    public void setDaoUser(String userName) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#fetchUser(org.dto.UserDto)
     */
    @Override
    public List<UserDto> fetchUser(UserDto user) throws UserDaoException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#maintainUser(org.dto.UserDto)
     */
    @Override
    public int maintainUser(UserDto user) throws UserDaoException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteUser(int)
     */
    @Override
    public int deleteUser(int uid) throws UserDaoException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#activateUser(java.lang.String)
     */
    @Override
    public int activateUser(String userName) throws UserDaoException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#inActivateUser(java.lang.String)
     */
    @Override
    public int inActivateUser(String userName) throws UserDaoException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#maintainGroup(org.dto.UserDto)
     */
    @Override
    public int maintainGroup(UserDto grp) throws UserDaoException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.user.UserDao#deleteGroup(int)
     */
    @Override
    public int deleteGroup(int grpId) throws UserDaoException {
        return 0;
    }

    @Override
    public List<UserDto> fetchGroup(UserDto group) throws UserDaoException {
        return null;
    }
}
